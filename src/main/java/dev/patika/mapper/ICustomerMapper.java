package dev.patika.mapper;

import dev.patika.dto.request.CustomerSaveRequest;
import dev.patika.dto.request.CustomerUpdateRequest;
import dev.patika.dto.response.CustomerResponse;
import dev.patika.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper
public interface ICustomerMapper {
    Customer asEntity(CustomerSaveRequest customerSaveRequest);

    CustomerResponse asOutput(Customer customer);

    List<CustomerResponse> asOutput(List<Customer> customers);

    void update(@MappingTarget Customer entity, CustomerUpdateRequest request);
}
