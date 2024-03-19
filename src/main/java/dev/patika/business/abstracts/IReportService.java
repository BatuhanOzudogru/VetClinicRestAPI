package dev.patika.business.abstracts;


import dev.patika.dto.request.ReportRequest;
import dev.patika.dto.response.standard.AppointmentResponse;
import dev.patika.dto.response.standard.ReportResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IReportService {

    Page<ReportResponse> cursor(int page, int size);
    ReportResponse getById(Long id);

    ReportResponse create(ReportRequest request);

    void delete(Long id);

    ReportResponse update(Long id, ReportRequest request);

    //ReportResponse getByAppointmentId(Long id);

}
