package dev.patika.business.abstracts;

import dev.patika.dto.request.DoctorRequest;
import dev.patika.dto.response.standard.DoctorResponse;
import org.springframework.data.domain.Page;

public interface IDoctorService {
    DoctorResponse getById (Long id);

    DoctorResponse create(DoctorRequest request);
    void delete(Long id);
    DoctorResponse update(Long id, DoctorRequest request);
    Page<DoctorResponse> cursor(int page, int size);


}
