package dev.patika.api;


import dev.patika.business.concretes.VaccineManager;
import dev.patika.core.result.Result;
import dev.patika.core.result.ResultData;
import dev.patika.core.utils.ResultHelper;
import dev.patika.dto.request.VaccineRequest;
import dev.patika.dto.response.standard.VaccineResponse;
import dev.patika.dto.response.pagination.CursorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {
    private final VaccineManager vaccineManager;

    @GetMapping("/by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> getById(@PathVariable("id") Long id) {
        return ResultHelper.success(vaccineManager.getById(id));
    }

    // Değerlendirme Formu 20 - Belirli bir hayvana ait tüm aşı kayıtları (sadece bir hayvanın tüm aşı kayıtları) listelenebiliyor mu?
    @GetMapping("/by-animal-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getByAnimalId(@PathVariable("id") Long id) {
        return ResultHelper.success(vaccineManager.getByAnimalId(id));
    }

    // Değerlendirme Formu 21 - Hayvanların aşı kayıtları, girilen tarih aralığına göre doğru şekilde listeleniyor mu?
    @GetMapping("/by-period")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getByPeriod(
            @RequestParam(name = "startDate", required = false, defaultValue = "2023-01-01")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "endDate", required = false, defaultValue = "2023-12-31")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        return ResultHelper.success(vaccineManager.getByPeriod(startDate, endDate));
    }

    // Değerlendirme Formu 15 - Proje isterlerine göre hayvana ait aşı kaydediliyor mu?
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineRequest request) {
        VaccineResponse vaccineResponse = vaccineManager.create(request);
        return ResultHelper.created(vaccineResponse);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@PathVariable Long id, @Valid @RequestBody VaccineRequest request) {
        VaccineResponse vaccineResponse = vaccineManager.update(id, request);
        return ResultHelper.updated(vaccineResponse);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        vaccineManager.delete(id);
        return ResultHelper.deleted();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ) {

        return ResultHelper.cursor(vaccineManager.cursor(page, size));

    }
}
