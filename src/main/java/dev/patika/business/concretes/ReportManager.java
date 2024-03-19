package dev.patika.business.concretes;

import dev.patika.business.abstracts.IReportService;
import dev.patika.core.config.mapper.ReportMapper;
import dev.patika.core.exception.NotFoundException;
import dev.patika.core.utils.Message;
import dev.patika.dal.IAppointmentRepo;
import dev.patika.dal.ReportRepository;
import dev.patika.dto.request.ReportRequest;
import dev.patika.dto.response.standard.ReportResponse;
import dev.patika.entity.Appointment;
import dev.patika.entity.Customer;
import dev.patika.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportManager implements IReportService {

    private final ReportRepository reportRepository;
    private final IAppointmentRepo appoinmentRepo;
    private final ReportMapper reportMapper;



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
        Optional<Report> ReportFromDb = reportRepository.findById(id);
        if (ReportFromDb.isPresent()) {
            reportRepository.delete(ReportFromDb.get());
        } else {
            throw new NotFoundException(Message.NOT_FOUND);
        }
    }

    @Override
    public ReportResponse update(Long id, ReportRequest request) {
        Optional<Report> reportFromDb = reportRepository.findById(id);
        //Appointment appointment = appoinmentRepo.findById(request.getAppointment().getId()).orElse(null);
        if (reportFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND);
        }

        Report report = reportFromDb.get();
        reportMapper.update(report, request);

        return reportMapper.convertToReportResponse(reportRepository.save(report));
    }

    @Override
    public Page<ReportResponse> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return  reportRepository.findAll(pageable).map(this.reportMapper::convertToReportResponse);
    }


}
