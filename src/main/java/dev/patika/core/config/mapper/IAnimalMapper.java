package dev.patika.core.config.mapper;

import dev.patika.dto.request.AnimalRequest;
import dev.patika.dto.response.standard.AnimalResponse;
import dev.patika.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper
public interface IAnimalMapper {
    Animal asEntity(AnimalRequest request);

    AnimalResponse asOutput(Animal animal);

    List<AnimalResponse> asOutput(List<Animal> animals);

    void update(@MappingTarget Animal entity, AnimalRequest request);

}
