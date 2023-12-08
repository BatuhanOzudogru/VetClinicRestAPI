package dev.patika.core.config.mapper;

import dev.patika.dto.request.AvailableDateRequest;
import dev.patika.dto.request.DoctorRequest;
import dev.patika.dto.response.AvailableDateResponse;
import dev.patika.dto.response.DoctorResponse;
import dev.patika.entity.AvailableDate;
import dev.patika.entity.Doctor;
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
