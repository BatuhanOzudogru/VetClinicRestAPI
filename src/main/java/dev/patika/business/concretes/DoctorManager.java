package dev.patika.business.concretes;

import dev.patika.business.abstracts.IDoctorService;
import dev.patika.core.config.mapper.IAvailableDateMapper;
import dev.patika.core.config.mapper.IDoctorMapper;
import dev.patika.core.exception.EntityExistsException;
import dev.patika.core.exception.NotFoundException;
import dev.patika.core.utils.Message;
import dev.patika.dal.IAvailableDateRepo;
import dev.patika.dal.IDoctorRepo;
import dev.patika.dto.request.DoctorRequest;
import dev.patika.dto.response.standard.DoctorResponse;
import dev.patika.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorManager implements IDoctorService {

    private final IDoctorRepo doctorRepo;
    private final IDoctorMapper doctorMapper;
    private final IAvailableDateRepo availableDateRepo;
    private final IAvailableDateMapper dateMapper;

    @Override
    public DoctorResponse getById(Long id) {

        return doctorMapper.asOutput(doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    // Değerlendirme Formu 12 - Proje isterlerine göre doktor kaydediliyor mu?
    @Override
    public DoctorResponse create(DoctorRequest request) {
        Optional<Doctor> isDoctorExist = doctorRepo.findByPhoneOrMail(request.getPhone(), request.getMail());

        if (isDoctorExist.isEmpty()) {
            Doctor doctorSaved = doctorRepo.save(doctorMapper.asEntity(request));
            return doctorMapper.asOutput(doctorSaved);
        }
        throw new EntityExistsException(Message.ALREADY_EXIST);

    }

    @Override
    public void delete(Long id) {
        Optional<Doctor> doctorFromDb = doctorRepo.findById(id);
        if (doctorFromDb.isPresent()) {
            doctorRepo.delete(doctorFromDb.get());
        } else {
            throw new NotFoundException(Message.NOT_FOUND);

        }
    }

    @Override
    public DoctorResponse update(Long id, DoctorRequest request) {
        Optional<Doctor> doctorFromDb = doctorRepo.findById(id);

        if (doctorFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND);
        }

        if (request.getName() == null || request.getPhone() == null) {
            throw new EntityExistsException(Message.ALREADY_EXIST);
        }

        Doctor doctor = doctorFromDb.get();
        doctorMapper.update(doctor, request);
        return doctorMapper.asOutput(doctorRepo.save(doctor));
    }
    @Override
    public Page<DoctorResponse> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.doctorRepo.findAll(pageable).map(this.doctorMapper::asOutput);
    }

}
