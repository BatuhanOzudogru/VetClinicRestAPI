package dev.patika.business.concretes;

import dev.patika.business.abstracts.IAppointmentService;
import dev.patika.core.config.mapper.IAppointmentMapper;
import dev.patika.core.exception.DoctorNotAvailableException;
import dev.patika.core.exception.NotFoundException;
import dev.patika.core.utils.Message;
import dev.patika.dal.IAppointmentRepo;
import dev.patika.dal.IAvailableDateRepo;
import dev.patika.dto.request.AppointmentRequest;
import dev.patika.dto.response.AppointmentResponse;
import dev.patika.entity.Appointment;
import dev.patika.entity.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {

    private final IAppointmentRepo appointmentRepo;
    private final IAppointmentMapper appointmentMapper;
    private final IAvailableDateRepo availableDateRepo;

    @Override
    public AppointmentResponse getById(Long id) {
        return appointmentMapper.asOutput(appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    @Override
    public AppointmentResponse create(AppointmentRequest request) {

//        Optional<Appointment> isAppointmentExist = appointmentRepo.findByDoctorAndAppointmentDate(request.getDoctor(), request.getAppointmentDate());
        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findByDoctorAndDate(request.getDoctor(), request.getAppointmentDate().toLocalDate());
        System.out.println("batu");
        System.out.println(request.getDoctor().getName());
        System.out.println(request.getAppointmentDate().toLocalDate());
        System.out.println("batu");

        if (isAvailableDateExist.isPresent()) {
            Appointment AppointmentSaved = appointmentRepo.save(appointmentMapper.asEntity(request));

            return appointmentMapper.asOutput(AppointmentSaved);
        }
        throw new DoctorNotAvailableException();

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
