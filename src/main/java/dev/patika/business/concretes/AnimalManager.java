package dev.patika.business.concretes;

import dev.patika.business.abstracts.AnimalService;

import dev.patika.core.config.mapper.AnimalMapper;
import dev.patika.core.exception.EntityExistsException;
import dev.patika.core.exception.NotFoundException;
import dev.patika.core.utils.Message;
import dev.patika.dal.AnimalRepo;

import dev.patika.dto.request.AnimalRequest;
import dev.patika.dto.response.standard.AnimalResponse;

import dev.patika.entity.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalManager implements AnimalService {

    private final AnimalRepo animalRepo;
    private final AnimalMapper animalMapper;

    @Override
    public AnimalResponse getById(Long id) {
        return animalMapper.asOutput(animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    @Override
    public List<AnimalResponse> getByName(String name) {
        return animalMapper.asOutput(animalRepo.findByNameIgnoreCaseLike(name).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    @Override
    public List<AnimalResponse> getByCustomerName(String name) {
        return animalMapper.asOutput(animalRepo.findByCustomerNameIgnoreCaseLike(name).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND)));
    }

    @Override
    public AnimalResponse create(AnimalRequest request) {

        Optional<Animal> isAnimalExist = animalRepo.findByNameAndCustomer(request.getName(), request.getCustomer());


        if (isAnimalExist.isEmpty()) {
            Animal animalSaved = animalRepo.save(animalMapper.asEntity(request));

            return animalMapper.asOutput(animalSaved);
        }
        throw new EntityExistsException(Message.ALREADY_EXIST);

    }


    @Override
    public void delete(Long id) {
        Optional<Animal> animalFromDb = animalRepo.findById(id);
        if (animalFromDb.isPresent()) {
            animalRepo.delete(animalFromDb.get());
        } else {
            throw new NotFoundException(Message.NOT_FOUND);

        }
    }

    @Override
    public AnimalResponse update(Long id, AnimalRequest request) {
        Optional<Animal> animalFromDb = animalRepo.findById(id);
        Optional<Animal> isAnimalExist = animalRepo.findByNameAndSpeciesAndBreedAndDateOfBirth(request.getName(), request.getSpecies(), request.getBreed(), request.getDateOfBirth());
        if (isAnimalExist.isPresent()) {
            throw new EntityExistsException(Message.ALREADY_EXIST);
        }
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND);
        }

        Animal animal = animalFromDb.get();
        animalMapper.update(animal, request);
        return animalMapper.asOutput(animalRepo.save(animal));
    }

    @Override
    public Page<AnimalResponse> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.animalRepo.findAll(pageable).map(this.animalMapper::asOutput);
    }
}
