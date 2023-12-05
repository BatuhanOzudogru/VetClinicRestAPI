package dev.patika.business.abstracts;

import dev.patika.dto.request.CustomerSaveRequest;
import dev.patika.dto.request.CustomerUpdateRequest;
import dev.patika.dto.response.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICustomerService {
    CustomerResponse getById (Long id);

    CustomerResponse create(CustomerSaveRequest customerSaveRequest);
    void delete(Long id);
    CustomerResponse update(Long id, CustomerUpdateRequest customerUpdateRequest);

    List<CustomerResponse> findAll ();
}
