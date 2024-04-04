package dev.patika.business.concretes;

import dev.patika.business.abstracts.VaccineService;
import dev.patika.core.config.mapper.VaccineMapper;
import dev.patika.core.exception.*;
import dev.patika.core.utils.Message;
import dev.patika.dal.AnimalRepo;
import dev.patika.dal.VaccineRepo;
import dev.patika.dal.ReportRepository;
import dev.patika.dto.request.VaccineRequest;
import dev.patika.dto.response.standard.VaccineResponse;
import dev.patika.entity.Animal;
import dev.patika.entity.Report;
import dev.patika.entity.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineManager implements VaccineService {

    private final VaccineRepo vaccineRepo;
    private final VaccineMapper vaccineMapper;
    private final AnimalRepo animalRepo;
    private final ReportRepository reportRepo;

    @Override
    public VaccineResponse getById(Long id) {
        return vaccineMapper.asOutput(vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    @Override
    public List<VaccineResponse> getByAnimalId(Long id) {

        if (id == null) {
            throw new AnimalSelectIdNullException();
        }

        Animal animal = animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
        return vaccineMapper.asOutput(vaccineRepo.findByAnimal(animal).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    @Override
    public List<VaccineResponse> getByPeriod(LocalDate startDate, LocalDate endDate) {

        if (startDate == null || endDate == null) {
            throw new DateSelectIdNullException();
        }

        return vaccineMapper.asOutput(vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate));
    }

    @Override
    public VaccineResponse create(VaccineRequest request) {

        if (request.getProtectionStartDate().isAfter(request.getProtectionFinishDate())) {
            throw new LocalDateException();
        }

        if (request.getAnimal().getId() == null) {
            throw new AnimalIdNullException();
        }

        if (request.getReport().getId() == null) {
            throw new ReportIdNullException();
        }

        Long requestAnimalId = request.getAnimal().getId();
        Long reportId = request.getReport().getId();
        Report vaccineReport = reportRepo.findById(reportId).orElse(null);
        Long reportAnimalId = vaccineReport.getAppointment().getAnimal().getId();

        List<Vaccine> isVaccineValid = vaccineRepo.findVaccinesAfterStartDate(request.getCode(), request.getAnimal().getId(), request.getProtectionStartDate());

        if (isVaccineValid.isEmpty()) {
            if(requestAnimalId.equals(reportAnimalId)) {

                Vaccine vaccineSaved = vaccineRepo.save(vaccineMapper.asEntity(request));

                return vaccineMapper.asOutput(vaccineSaved);
            }else {
                throw new AnimalsDontMatchException();
            }
        }
        throw new VaccineExistsException();

    }


    @Override
    public void delete(Long id) {
        Optional<Vaccine> VaccineFromDb = vaccineRepo.findById(id);
        if (VaccineFromDb.isPresent()) {
            vaccineRepo.delete(VaccineFromDb.get());
        } else {
            throw new NotFoundException(Message.NOT_FOUND);

        }
    }

    @Override
    public VaccineResponse update(Long id, VaccineRequest request) {
        if (request.getAnimal().getId() == null) {
            throw new AnimalIdNullException();
        }

        if (request.getReport().getId() == null) {
            throw new ReportIdNullException();
        }

        Optional<Vaccine> vaccineFromDb = vaccineRepo.findById(id);

        if (vaccineFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND);
        }

        Vaccine vaccine = vaccineFromDb.get();

        if (request.getProtectionStartDate().isAfter(request.getProtectionFinishDate())) {
            throw new LocalDateException();
        }

        Long requestAnimalId = request.getAnimal().getId();
        Long reportId = request.getReport().getId();
        Report vaccineReport = reportRepo.findById(reportId).orElse(null);
        Long reportAnimalId = vaccineReport.getAppointment().getAnimal().getId();




        if (requestAnimalId.equals(reportAnimalId)) {
            vaccineMapper.update(vaccine, request);
            return vaccineMapper.asOutput(vaccineRepo.save(vaccine));
        } else {
            throw new AnimalsDontMatchException();
        }

    }


    @Override
    public Page<VaccineResponse> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.vaccineRepo.findAll(pageable).map(this.vaccineMapper::asOutput);
    }
}
