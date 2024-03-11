package dev.patika.core.config.mapper;


import dev.patika.dto.request.ReportRequest;
import dev.patika.dto.response.standard.ReportResponse;
import dev.patika.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface ReportMapper {
    Report convertToReport(ReportRequest request);

    ReportResponse convertToReportResponse(Report report);

    List<ReportResponse> convertToReportResponseList(List<Report> reportList);

    void update(@MappingTarget Report report, ReportRequest request);
}
