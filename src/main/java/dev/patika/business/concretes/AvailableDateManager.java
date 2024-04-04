package dev.patika.business.concretes;

import dev.patika.business.abstracts.AvailableDateService;
import dev.patika.core.config.mapper.AvailableDateMapper;
import dev.patika.core.exception.DoctorIdNullException;
import dev.patika.core.exception.EntityExistsException;
import dev.patika.core.exception.NotFoundException;
import dev.patika.core.utils.Message;
import dev.patika.dal.AvailableDateRepo;
import dev.patika.dto.request.AvailableDateRequest;
import dev.patika.dto.response.standard.AvailableDateResponse;
import dev.patika.entity.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements AvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final AvailableDateMapper availableDateMapper;

    @Override
    public AvailableDateResponse getById(Long id) {
        return availableDateMapper.asOutput(availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    @Override
    public AvailableDateResponse create(AvailableDateRequest request) {

        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findByDate(request.getDate());

        if (request.getDoctor().getId() == null) {
            throw new DoctorIdNullException();
        }

        if (isAvailableDateExist.isEmpty()) {
            AvailableDate availableDateSaved = availableDateRepo.save(availableDateMapper.asEntity(request));
            return availableDateMapper.asOutput(availableDateSaved);
        }
        throw new EntityExistsException(Message.ALREADY_EXIST);

    }


    @Override
    public void delete(Long id) {
        Optional<AvailableDate> availableDateFromDb = availableDateRepo.findById(id);
        if (availableDateFromDb.isPresent()) {
            availableDateRepo.delete(availableDateFromDb.get());
        } else {
            throw new NotFoundException(Message.NOT_FOUND);

        }
    }

    @Override
    public AvailableDateResponse update(Long id, AvailableDateRequest request) {
        Optional<AvailableDate> availableDateFromDb = availableDateRepo.findById(id);
        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findByDate(request.getDate());

        if (isAvailableDateExist.isPresent()) {
            throw new EntityExistsException(Message.ALREADY_EXIST);
        }

        if (availableDateFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND);
        }

        AvailableDate availableDate = availableDateFromDb.get();
        availableDateMapper.update(availableDate, request);
        return availableDateMapper.asOutput(availableDateRepo.save(availableDate));
    }

    @Override
    public Page<AvailableDateResponse> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.availableDateRepo.findAll(pageable).map(this.availableDateMapper::asOutput);
    }
}
