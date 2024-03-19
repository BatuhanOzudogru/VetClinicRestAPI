package dev.patika.api;

import dev.patika.business.concretes.ReportManager;
import dev.patika.core.result.Result;
import dev.patika.core.result.ResultData;
import dev.patika.core.utils.ResultHelper;
import dev.patika.dto.request.ReportRequest;
import dev.patika.dto.response.pagination.CursorResponse;
import dev.patika.dto.response.standard.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportManager reportManager;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<ReportResponse> create(@RequestBody ReportRequest request){
        return ResultHelper.created(reportManager.create(request));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReportResponse> update(@PathVariable Long id,@RequestBody ReportRequest request){
        return ResultHelper.updated(reportManager.update(id,request));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable Long id){
        reportManager.delete(id);
       return ResultHelper.deleted();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<ReportResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10000") int size
    ){
        return ResultHelper.cursor(reportManager.cursor(page, size));
    }
}
