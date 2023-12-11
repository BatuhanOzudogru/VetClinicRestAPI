package dev.patika.core.config.mapper;

import dev.patika.dto.request.AnimalRequest;
import dev.patika.dto.request.VaccineRequest;
import dev.patika.dto.response.AnimalResponse;
import dev.patika.dto.response.VaccineResponse;
import dev.patika.entity.Animal;
import dev.patika.entity.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper
public interface IVaccineMapper {
    Vaccine asEntity(VaccineRequest request);

    VaccineResponse asOutput(Vaccine vaccine);

    List<VaccineResponse> asOutput(List<Vaccine> vaccineList);

    void update(@MappingTarget Vaccine entity, VaccineRequest request);

}
