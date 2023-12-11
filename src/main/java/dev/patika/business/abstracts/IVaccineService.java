package dev.patika.business.abstracts;

import dev.patika.dto.request.VaccineRequest;
import dev.patika.dto.response.VaccineResponse;
import org.springframework.data.domain.Page;

public interface IVaccineService {
    VaccineResponse getById(Long id);

//    VaccineResponse getByName(String name);

    VaccineResponse create(VaccineRequest request);

    void delete(Long id);

    VaccineResponse update(Long id, VaccineRequest request);

    Page<VaccineResponse> cursor(int page, int size);
}
