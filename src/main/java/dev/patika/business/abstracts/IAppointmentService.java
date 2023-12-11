package dev.patika.business.abstracts;

import dev.patika.dto.request.AnimalRequest;
import dev.patika.dto.request.AppointmentRequest;
import dev.patika.dto.response.AnimalResponse;
import dev.patika.dto.response.AppointmentResponse;
import org.springframework.data.domain.Page;

public interface IAppointmentService {
    AppointmentResponse getById(Long id);

    AppointmentResponse create(AppointmentRequest request);

    void delete(Long id);

    AppointmentResponse update(Long id, AppointmentRequest request);

    Page<AppointmentResponse> cursor(int page, int size);
}
