package dev.patika.api;

import dev.patika.business.concretes.CustomerManager;
import dev.patika.core.result.Result;
import dev.patika.core.result.ResultData;
import dev.patika.core.utils.ResultHelper;
import dev.patika.dto.request.CustomerRequest;
import dev.patika.dto.response.pagination.CursorResponse;
import dev.patika.dto.response.standard.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerManager customerManager;

    // Değerlendirme Formu 18 - Girilen hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntüleme
    // (sadece bir kişiye ait hayvanları görüntüle işlemi) başarılı bir şekilde çalışıyor mu?
    @GetMapping("/by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getById(@PathVariable("id") Long id) {
        return ResultHelper.success(customerManager.getById(id));
    }

    // Değerlendirme Formu 17 - Hayvan sahipleri isme göre filtreleniyor mu?
    // Değerlendirme Formu 18 - Girilen hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntüleme
    // (sadece bir kişiye ait hayvanları görüntüle işlemi) başarılı bir şekilde çalışıyor mu?
    @GetMapping("/by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getByName(@PathVariable("name") String name) {
        return ResultHelper.success(customerManager.getByName(name));
    }

    // Değerlendirme Formu 10 - Proje isterlerine göre hayvan sahibi kaydediliyor mu?
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerManager.create(customerRequest);
        return ResultHelper.created(customerResponse);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@PathVariable Long id,@Valid @RequestBody CustomerRequest request) {
        CustomerResponse customerResponse = customerManager.update(id, request);
        return ResultHelper.updated(customerResponse);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        customerManager.delete(id);
        return ResultHelper.deleted();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ){

        return ResultHelper.cursor(customerManager.cursor(page, size));

    }
}
