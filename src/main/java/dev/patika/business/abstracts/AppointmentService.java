package dev.patika.business.abstracts;

import dev.patika.dto.request.AppointmentRequest;
import dev.patika.dto.response.standard.AppointmentResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface AppointmentService {
    AppointmentResponse getById(Long id);

    List<AppointmentResponse> getByDoctorIdAndAppointmentDate(Long id, LocalDateTime startDate, LocalDateTime endDate);

    List<AppointmentResponse> getByAnimalIdAndAppointmentDate(Long id, LocalDate startDate, LocalDate endDate);

    AppointmentResponse create(AppointmentRequest request);

    void delete(Long id);

    AppointmentResponse update(Long id, AppointmentRequest request);

    Page<AppointmentResponse> cursor(int page, int size);
}
