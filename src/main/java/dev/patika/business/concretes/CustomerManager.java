package dev.patika.business.concretes;

import dev.patika.business.abstracts.ICustomerService;
import dev.patika.dal.ICustomerRepo;
import dev.patika.dto.request.CustomerSaveRequest;
import dev.patika.dto.request.CustomerUpdateRequest;
import dev.patika.dto.response.CustomerResponse;
import dev.patika.entity.Customer;
import dev.patika.mapper.ICustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerManager implements ICustomerService {

    private final ICustomerRepo customerRepo;
    private final ICustomerMapper customerMapper;

    @Override
    public CustomerResponse getById(Long id) {
        return customerMapper.asOutput(customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Sistemde böyle bir müşteri bulunamadı!")));
    }

    @Override
    public CustomerResponse create(CustomerSaveRequest customerSaveRequest) {
        Optional<Customer> isCustomerExits = customerRepo.findByName(customerSaveRequest.getName());

        if (isCustomerExits.isEmpty()) {
            Customer customerSaved = customerRepo.save(customerMapper.asEntity(customerSaveRequest));
            return customerMapper.asOutput(customerSaved);
        }
        throw new RuntimeException("Sistemde aynı bilgilere sahip bir müşteri yer almaktadır !!!");
    }


    @Override
    public void delete(Long id) {
        Optional<Customer> customerFromDb = customerRepo.findById(id);
        if (customerFromDb.isPresent()) {
            customerRepo.delete(customerFromDb.get());
        } else {
            throw new RuntimeException("Sistemde böyle bir müşteri bulunamadı!");
        }
    }

    @Override
    public CustomerResponse update(Long id, CustomerUpdateRequest customerUpdateRequest) {
        Optional<Customer> customerFromDb = customerRepo.findById(id);

        if (customerFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız müşteri sistemde bulunamadı!!!.");
        }

        Customer customer = customerFromDb.get();
        customerMapper.update(customer, customerUpdateRequest);
        return customerMapper.asOutput(customerRepo.save(customer));
    }

    @Override
    public List<CustomerResponse> findAll() {
        return customerMapper.asOutput(customerRepo.findAll());
    }
}
