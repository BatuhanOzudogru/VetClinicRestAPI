package dev.patika.api;

import dev.patika.business.concretes.CustomerManager;
import dev.patika.business.concretes.DoctorManager;
import dev.patika.core.result.Result;
import dev.patika.core.result.ResultData;
import dev.patika.core.utils.ResultHelper;
import dev.patika.dto.request.CustomerRequest;
import dev.patika.dto.request.DoctorRequest;
import dev.patika.dto.response.CursorResponse;
import dev.patika.dto.response.CustomerResponse;
import dev.patika.dto.response.DoctorResponse;
import dev.patika.entity.AvailableDate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorManager doctorManager;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> getById(@PathVariable("id") Long id) {
        return ResultHelper.success(doctorManager.getById(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorRequest doctorRequest) {
        DoctorResponse doctorResponse = doctorManager.create(doctorRequest);
        return ResultHelper.created(doctorResponse);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@PathVariable Long id, @Valid @RequestBody DoctorRequest request) {
        DoctorResponse doctorResponse = doctorManager.update(id, request);
        return ResultHelper.updated(doctorResponse);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        doctorManager.delete(id);
        return ResultHelper.deleted();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ){

        return ResultHelper.cursor(doctorManager.cursor(page, size));

    }
}
