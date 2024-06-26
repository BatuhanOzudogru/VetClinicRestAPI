package dev.patika.business.concretes;

import dev.patika.business.abstracts.AppointmentService;
import dev.patika.core.config.mapper.AppointmentMapper;
import dev.patika.core.exception.*;
import dev.patika.core.utils.Message;
import dev.patika.dal.*;
import dev.patika.dto.request.AppointmentRequest;
import dev.patika.dto.response.standard.AppointmentResponse;
import dev.patika.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final AppointmentMapper appointmentMapper;
    private final AvailableDateRepo availableDateRepo;
    private final DoctorRepo doctorRepo;
    private final AnimalRepo animalRepo;
    private final ReportRepository reportRepository;

    @Override
    public AppointmentResponse getById(Long id) {
        return appointmentMapper.asOutput(appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }
    // Değerlendirme Formu 24 - Randevular kullanıcı tarafından girilen tarih aralığına ve doktora göre filtreleniyor mu?
    @Override
    public List<AppointmentResponse> getByDoctorIdAndAppointmentDate(Long id, LocalDateTime startDate, LocalDateTime endDate) {

        Doctor doctor = doctorRepo.findById(id).orElseThrow();
        return appointmentMapper.asOutput(appointmentRepo.findByDoctorAndAppointmentDateBetween(doctor,startDate, endDate).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    // Değerlendirme Formu 23 - Randevular kullanıcı tarafından girilen tarih aralığına ve hayvana göre filtreleniyor mu?
    @Override
    public List<AppointmentResponse> getByAnimalIdAndAppointmentDate(Long id, LocalDate startDate, LocalDate endDate) {

        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(LocalTime.MAX);

        Animal animal = animalRepo.findById(id).orElseThrow();
        return appointmentMapper.asOutput(appointmentRepo.findByAnimalAndAppointmentDateBetween(animal,startOfDay, endOfDay).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    // Değerlendirme Formu 14 - Proje isterlerine göre randevu kaydediliyor mu?
    @Override
    public AppointmentResponse create(AppointmentRequest request) {

        if (request.getDoctor().getId() == null) {
            throw new DoctorIdNullException();
        }

        if (request.getAnimal().getId() == null) {
            throw new AnimalIdNullException();
        }

        // Değerlendirme Formu 22 - Randevu oluşturulurken, doktorun o saatte başka bir randevusu var mı, doktorun müsait günü var mı  kontrolü yapılıyor mu?
        // Sadece randevusu yoksa ve müsait günü varsa randevu kaydına izin veriyor mu?
        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findByDoctorAndDate(request.getDoctor(), request.getAppointmentDate().toLocalDate());
        Optional<Appointment> isAppointmentExist = appointmentRepo.findByDoctorAndAnimalAndAppointmentDate(request.getDoctor(), request.getAnimal(), request.getAppointmentDate());
        Optional<Appointment> isAppointmentTaken = appointmentRepo.findByDoctorAndAppointmentDate(request.getDoctor(), request.getAppointmentDate());


        if (request.getAppointmentDate().getMinute() != 0) {

            throw new AppointmentTimeException();

        } else if (isAppointmentExist.isPresent()) {

            throw new AppointmentExistsException();

        } else if (isAvailableDateExist.isEmpty()) {

            throw new DoctorNotAvailableException();

        } else if (isAppointmentTaken.isPresent()) {

            throw new AppointmentNotAvailableException();

        } else {

            Appointment AppointmentSaved = appointmentRepo.save(appointmentMapper.asEntity(request));
            return appointmentMapper.asOutput(AppointmentSaved);

        }


    }


    @Override
    public void delete(Long id) {
        Optional<Appointment> AppointmentFromDb = appointmentRepo.findById(id);


        if (AppointmentFromDb.isPresent()) {
         Optional<Report> report =  reportRepository.findReportByAppointmentId(id);

         if (report.isPresent()) {

             throw new ReportExistException();

         } else {
             appointmentRepo.delete(AppointmentFromDb.get());
         }

        } else {
            throw new NotFoundException(Message.NOT_FOUND);

        }
    }

    @Override
    public AppointmentResponse update(Long id, AppointmentRequest request) {

        if (request.getDoctor().getId() == null) {
            throw new DoctorIdNullException();
        }

        if (request.getAnimal().getId() == null) {
            throw new AnimalIdNullException();
        }

        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);

        if (appointmentFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND);
        }

        Appointment appointment = appointmentFromDb.get();

        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findByDoctorAndDate(request.getDoctor(), request.getAppointmentDate().toLocalDate());
        Optional<Appointment> isAppointmentExist = appointmentRepo.findByDoctorAndAnimalAndAppointmentDate(request.getDoctor(), request.getAnimal(), request.getAppointmentDate());
        Optional<Appointment> isAppointmentTaken = appointmentRepo.findByDoctorAndAppointmentDate(request.getDoctor(), request.getAppointmentDate());

        if (request.getAppointmentDate().getMinute() != 0) {
            throw new AppointmentTimeException();
        } else if (isAppointmentExist.isPresent()) {
            throw new AppointmentExistsException();
        } else if (isAvailableDateExist.isEmpty()) {
            throw new DoctorNotAvailableException();
        } else if (isAppointmentTaken.isPresent() && !isAppointmentTaken.get().getId().equals(id)) {
            throw new AppointmentNotAvailableException();
        }


        appointmentMapper.update(appointment, request);
        return appointmentMapper.asOutput(appointmentRepo.save(appointment));
    }


    @Override
    public Page<AppointmentResponse> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.appointmentRepo.findAll(pageable).map(this.appointmentMapper::asOutput);
    }
}
