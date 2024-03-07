package dev.patika.api;

import dev.patika.business.concretes.ReportManager;
import dev.patika.dto.request.ReportRequest;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReportResponse> getAll(){
        return reportManager.getAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ReportResponse create(@RequestBody ReportRequest request){
        return reportManager.create(request);
    }
}