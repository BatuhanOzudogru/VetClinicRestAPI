package dev.patika.business.concretes;

import dev.patika.business.abstracts.IVaccineService;
import dev.patika.core.config.mapper.IVaccineMapper;
import dev.patika.core.exception.LocalDateException;
import dev.patika.core.exception.NotFoundException;
import dev.patika.core.exception.VaccineExistsException;
import dev.patika.core.utils.Message;
import dev.patika.dal.IVaccineRepo;
import dev.patika.dto.request.VaccineRequest;
import dev.patika.dto.response.VaccineResponse;
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
public class VaccineManager implements IVaccineService {

    private final IVaccineRepo vaccineRepo;
    private final IVaccineMapper vaccineMapper;

    @Override
    public VaccineResponse getById(Long id) {
        return vaccineMapper.asOutput(vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    @Override
    public List<VaccineResponse> getByProtectionFinishDate(LocalDate startDate, LocalDate endDate) {
        return vaccineMapper.asOutput(vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

//    @Override
//    public VaccineResponse getByName(String name) {
//        return vaccineMapper.asOutput(vaccineRepo.findByName(name).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
//    }

    @Override
    public VaccineResponse create(VaccineRequest request) {

        if (request.getProtectionStartDate().isAfter(request.getProtectionFinishDate())){
            throw new LocalDateException();
        }

        List<Vaccine> isVaccineValid = vaccineRepo.findVaccinesAfterStartDate(request.getCode(), request.getAnimal().getId(), request.getProtectionStartDate());


        if (isVaccineValid.isEmpty()) {

                Vaccine vaccineSaved = vaccineRepo.save(vaccineMapper.asEntity(request));

                return vaccineMapper.asOutput(vaccineSaved);

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
        Optional<Vaccine> VaccineFromDb = vaccineRepo.findById(id);

        if (VaccineFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND);
        }

//        if (request.getAvailableDate() == null || request.getDoctor().getId() == null) {
//            throw new EntityExistsException(Message.ALREADY_EXIST);
//        }

        Vaccine Vaccine = VaccineFromDb.get();
        vaccineMapper.update(Vaccine, request);
        return vaccineMapper.asOutput(vaccineRepo.save(Vaccine));
    }

    @Override
    public Page<VaccineResponse> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.vaccineRepo.findAll(pageable).map(this.vaccineMapper::asOutput);
    }
}
