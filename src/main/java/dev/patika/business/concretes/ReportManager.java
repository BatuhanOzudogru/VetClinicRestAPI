package dev.patika.business.concretes;

import dev.patika.business.abstracts.ReportService;
import dev.patika.core.config.mapper.ReportMapper;
import dev.patika.core.exception.NotFoundException;
import dev.patika.core.utils.Message;
import dev.patika.dal.IAppointmentRepo;
import dev.patika.dal.ReportRepository;
import dev.patika.dto.request.ReportRequest;
import dev.patika.dto.response.standard.ReportResponse;
import dev.patika.entity.Appointment;
import dev.patika.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportManager implements ReportService {

    private final ReportRepository reportRepository;
    private final IAppointmentRepo appoinmentRepo;
    private final ReportMapper reportMapper;


    @Override
    public List<ReportResponse> getAll() {
        List<Report> reportList = reportRepository.findAll();

        return  reportMapper.convertToReportResponseList(reportList);
    }

    @Override
    public ReportResponse getById(Long id) {
        Report report = reportRepository.findById(id).orElse(null);
        return reportMapper.convertToReportResponse(report);

    }

    @Override
    public ReportResponse create(ReportRequest request) {
          Report report = reportMapper.convertToReport(request);
          reportRepository.save(report);
          return reportMapper.convertToReportResponse(report);
    }

    @Override
    public void delete(Long id) {
        reportRepository.deleteById(id);

    }

    @Override
    public ReportResponse update(Long id, ReportRequest request) {
        Report oldReport = reportRepository.findById(id).orElse(null);
        //Appointment appointment = appoinmentRepo.findById(request.getAppointment().getId()).orElse(null);
        if (oldReport == null) {
            throw new NotFoundException(Message.NOT_FOUND);
        }

        oldReport.setTitle(request.getTitle());
        oldReport.setPrice(request.getPrice());
        oldReport.setDiagnosis(request.getDiagnosis());
        oldReport.setAppointment(request.getAppointment());

        reportRepository.save(oldReport);

        return reportMapper.convertToReportResponse(oldReport);
    }

}
