package dev.patika.api;

import dev.patika.business.concretes.AnimalManager;
import dev.patika.core.result.Result;
import dev.patika.core.result.ResultData;
import dev.patika.core.utils.ResultHelper;
import dev.patika.dto.request.AnimalRequest;
import dev.patika.dto.response.standard.AnimalResponse;
import dev.patika.dto.response.pagination.CursorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalManager animalManager;

    @GetMapping("/by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getById(@PathVariable("id") Long id) {
        return ResultHelper.success(animalManager.getById(id));
    }

    // Değerlendirme Formu 16 - Hayvanlar isme göre filtreleniyor mu?
    @GetMapping("/by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getByName(@PathVariable("name") String name) {
        return ResultHelper.success(animalManager.getByName(name));
    }

    // Değerlendirme Formu 11 - Proje isterlerine göre hayvan kaydediliyor mu?
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalRequest request) {
        AnimalResponse AnimalResponse = animalManager.create(request);
        return ResultHelper.created(AnimalResponse);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@PathVariable Long id,@Valid @RequestBody AnimalRequest request) {
        AnimalResponse AnimalResponse = animalManager.update(id, request);
        return ResultHelper.updated(AnimalResponse);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        animalManager.delete(id);
        return ResultHelper.deleted();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    ){

        return ResultHelper.cursor(animalManager.cursor(page, size));

    }
}
