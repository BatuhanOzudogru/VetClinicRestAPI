package dev.patika.business.abstracts;

import dev.patika.dto.request.CustomerRequest;
import dev.patika.dto.response.CustomerResponse;
import org.springframework.data.domain.Page;


public interface ICustomerService {
    CustomerResponse getById (Long id);
    CustomerResponse getByName (String name);

    CustomerResponse create(CustomerRequest request);
    void delete(Long id);
    CustomerResponse update(Long id, CustomerRequest request);
    Page<CustomerResponse> cursor(int page, int size);
}
