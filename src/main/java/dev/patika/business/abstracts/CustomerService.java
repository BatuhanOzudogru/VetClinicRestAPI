package dev.patika.business.abstracts;

import dev.patika.dto.request.CustomerRequest;
import dev.patika.dto.response.standard.CustomerResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CustomerService {
    CustomerResponse getById (Long id);

    List<CustomerResponse> getByName (String name);

    CustomerResponse create(CustomerRequest request);

    void delete(Long id);

    CustomerResponse update(Long id, CustomerRequest request);

    Page<CustomerResponse> cursor(int page, int size);
}
