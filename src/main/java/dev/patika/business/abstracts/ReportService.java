package dev.patika.business.abstracts;


import dev.patika.dto.request.ReportRequest;
import dev.patika.dto.response.standard.ReportResponse;

import java.util.List;

public interface ReportService {

    List<ReportResponse> getAll();
    ReportResponse getById(Long id);

    ReportResponse create(ReportRequest request);

    void delete(Long id);

    ReportResponse update(Long id, ReportRequest request);

    //ReportResponse getByAppointmentId(Long id);

}
