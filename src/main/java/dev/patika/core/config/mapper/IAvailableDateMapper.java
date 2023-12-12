package dev.patika.core.config.mapper;

import dev.patika.dto.request.AvailableDateRequest;
import dev.patika.dto.response.standard.AvailableDateResponse;
import dev.patika.entity.AvailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper
public interface IAvailableDateMapper {
    AvailableDate asEntity(AvailableDateRequest request);

    AvailableDateResponse asOutput(AvailableDate availableDate);

    List<AvailableDateResponse> asOutput(List<AvailableDate> availableDates);

    void update(@MappingTarget AvailableDate entity, AvailableDateRequest request);

//    default Page<DoctorResponse> mapToDoctorResponsePage(Page<Doctor> doctorPage) {
//        return doctorPage.map(this::asOutput);
//    }
}
