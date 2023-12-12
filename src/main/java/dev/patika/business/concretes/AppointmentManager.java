package dev.patika.business.concretes;

import dev.patika.business.abstracts.IAppointmentService;
import dev.patika.core.config.mapper.IAppointmentMapper;
import dev.patika.core.exception.*;
import dev.patika.core.utils.Message;
import dev.patika.dal.IAnimalRepo;
import dev.patika.dal.IAppointmentRepo;
import dev.patika.dal.IAvailableDateRepo;
import dev.patika.dal.IDoctorRepo;
import dev.patika.dto.request.AppointmentRequest;
import dev.patika.dto.response.standard.AppointmentResponse;
import dev.patika.entity.Animal;
import dev.patika.entity.Appointment;
import dev.patika.entity.AvailableDate;
import dev.patika.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {

    private final IAppointmentRepo appointmentRepo;
    private final IAppointmentMapper appointmentMapper;
    private final IAvailableDateRepo availableDateRepo;
    private final IDoctorRepo doctorRepo;
    private final IAnimalRepo animalRepo;

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
    public List<AppointmentResponse> getByAnimalIdAndAppointmentDate(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        Animal animal = animalRepo.findById(id).orElseThrow();
        return appointmentMapper.asOutput(appointmentRepo.findByAnimalAndAppointmentDateBetween(animal,startDate, endDate).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    // Değerlendirme Formu 14 - Proje isterlerine göre randevu kaydediliyor mu?
    @Override
    public AppointmentResponse create(AppointmentRequest request) {

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
            appointmentRepo.delete(AppointmentFromDb.get());
        } else {
            throw new NotFoundException(Message.NOT_FOUND);

        }
    }

    @Override
    public AppointmentResponse update(Long id, AppointmentRequest request) {
        Optional<Appointment> AppointmentFromDb = appointmentRepo.findById(id);

        if (AppointmentFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND);
        }

//        if (request.getAvailableDate() == null || request.getDoctor().getId() == null) {
//            throw new EntityExistsException(Message.ALREADY_EXIST);
//        }

        Appointment appointment = AppointmentFromDb.get();
        appointmentMapper.update(appointment, request);
        return appointmentMapper.asOutput(appointmentRepo.save(appointment));
    }

    @Override
    public Page<AppointmentResponse> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.appointmentRepo.findAll(pageable).map(this.appointmentMapper::asOutput);
    }
}
