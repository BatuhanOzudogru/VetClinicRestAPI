package dev.patika.core.config.mapper;

import dev.patika.dto.request.CustomerRequest;
import dev.patika.dto.response.CustomerResponse;
import dev.patika.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper
public interface ICustomerMapper {
    Customer asEntity(CustomerRequest request);

    CustomerResponse asOutput(Customer customer);

    List<CustomerResponse> asOutput(List<Customer> customers);

    void update(@MappingTarget Customer entity, CustomerRequest request);

//    default Page<CustomerResponse> mapToCustomerResponsePage(Page<Customer> customerPage) {
//        return customerPage.map(this::asOutput);
//    }
}
