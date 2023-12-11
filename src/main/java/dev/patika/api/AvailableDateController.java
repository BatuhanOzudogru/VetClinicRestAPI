package dev.patika.api;

import dev.patika.business.concretes.AvailableDateManager;
import dev.patika.core.result.Result;
import dev.patika.core.result.ResultData;
import dev.patika.core.utils.ResultHelper;
import dev.patika.dto.request.AvailableDateRequest;
import dev.patika.dto.request.CustomerRequest;
import dev.patika.dto.response.AvailableDateResponse;
import dev.patika.dto.response.CursorResponse;
import dev.patika.dto.response.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/dates")
@RequiredArgsConstructor
public class AvailableDateController {
    private final AvailableDateManager availableDateManager;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> getById(@PathVariable("id") Long id) {
        return ResultHelper.success(availableDateManager.getById(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateRequest availableDateRequest) {
        AvailableDateResponse availableDateResponse = availableDateManager.create(availableDateRequest);
        return ResultHelper.created(availableDateResponse);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@PathVariable Long id,@Valid @RequestBody AvailableDateRequest request) {
        AvailableDateResponse availableDateResponse = availableDateManager.update(id, request);
        return ResultHelper.updated(availableDateResponse);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        availableDateManager.delete(id);
        return ResultHelper.deleted();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ){

        return ResultHelper.cursor(availableDateManager.cursor(page, size));

    }
}
