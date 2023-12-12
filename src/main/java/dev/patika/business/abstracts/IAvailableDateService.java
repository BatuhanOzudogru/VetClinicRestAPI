package dev.patika.business.abstracts;

import dev.patika.dto.request.AvailableDateRequest;
import dev.patika.dto.response.standard.AvailableDateResponse;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    AvailableDateResponse getById (Long id);

    AvailableDateResponse create(AvailableDateRequest request);
    void delete(Long id);
    AvailableDateResponse update(Long id, AvailableDateRequest request);
    Page<AvailableDateResponse> cursor(int page, int size);
}
