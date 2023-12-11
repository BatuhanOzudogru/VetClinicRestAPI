package dev.patika.business.concretes;

import dev.patika.business.abstracts.IAvailableDateService;
import dev.patika.core.config.mapper.IAvailableDateMapper;
import dev.patika.core.exception.EntityExistsException;
import dev.patika.core.exception.NotFoundException;
import dev.patika.core.utils.Message;
import dev.patika.dal.IAvailableDateRepo;
import dev.patika.dal.IDoctorRepo;
import dev.patika.dto.request.AvailableDateRequest;
import dev.patika.dto.response.AvailableDateResponse;
import dev.patika.entity.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements IAvailableDateService {

    private final IAvailableDateRepo availableDateRepo;
    private final IAvailableDateMapper availableDateMapper;

    @Override
    public AvailableDateResponse getById(Long id) {
        return availableDateMapper.asOutput(availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    @Override
    public AvailableDateResponse create(AvailableDateRequest request) {

        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findByDate(request.getDate());

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

        if (availableDateFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND);
        }

//        if (request.getAvailableDate() == null || request.getDoctor().getId() == null) {
//            throw new EntityExistsException(Message.ALREADY_EXIST);
//        }

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
