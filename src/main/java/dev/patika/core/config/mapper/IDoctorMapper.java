package dev.patika.core.config.mapper;

import dev.patika.dto.request.DoctorRequest;
import dev.patika.dto.response.standard.DoctorResponse;
import dev.patika.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper
public interface IDoctorMapper {
    Doctor asEntity(DoctorRequest request);

    DoctorResponse asOutput(Doctor doctor);

    List<DoctorResponse> asOutput(List<Doctor> doctors);

    void update(@MappingTarget Doctor entity, DoctorRequest request);

//    default Page<DoctorResponse> mapToDoctorResponsePage(Page<Doctor> doctorPage) {
//        return doctorPage.map(this::asOutput);
//    }
}
