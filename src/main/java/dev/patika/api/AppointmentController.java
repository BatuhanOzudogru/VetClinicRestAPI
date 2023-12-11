package dev.patika.api;

import dev.patika.business.concretes.AppointmentManager;
import dev.patika.core.result.Result;
import dev.patika.core.result.ResultData;
import dev.patika.core.utils.ResultHelper;
import dev.patika.dto.request.AppointmentRequest;
import dev.patika.dto.response.AppointmentResponse;
import dev.patika.dto.response.CursorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentManager appointmentManager;

    @GetMapping("/by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> getById(@PathVariable("id") Long id) {
        return ResultHelper.success(appointmentManager.getById(id));
    }

    @GetMapping("/by-doctor-and-period/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getByDoctorIdAndAppointmentDate(
            @PathVariable("id") Long id,
            @RequestParam(name = "startDate", required = false, defaultValue = "2023-01-01")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "endDate", required = false, defaultValue = "2023-12-31")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        return ResultHelper.success(appointmentManager.getByDoctorIdAndAppointmentDate(id, startDate.atStartOfDay(), endDate.atStartOfDay()));
    }

    @GetMapping("/by-animal-and-period/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getByAnimalIdAndAppointmentDate(
            @PathVariable("id") Long id,
            @RequestParam(name = "startDate", required = false, defaultValue = "2023-01-01")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "endDate", required = false, defaultValue = "2023-12-31")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        return ResultHelper.success(appointmentManager.getByAnimalIdAndAppointmentDate(id, startDate.atStartOfDay(), endDate.atStartOfDay()));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentRequest request) {
        AppointmentResponse animalResponse = appointmentManager.create(request);
        return ResultHelper.created(animalResponse);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@PathVariable Long id, @Valid @RequestBody AppointmentRequest request) {
        AppointmentResponse animalResponse = appointmentManager.update(id, request);
        return ResultHelper.updated(animalResponse);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        appointmentManager.delete(id);
        return ResultHelper.deleted();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {

        return ResultHelper.cursor(appointmentManager.cursor(page, size));

    }
}
