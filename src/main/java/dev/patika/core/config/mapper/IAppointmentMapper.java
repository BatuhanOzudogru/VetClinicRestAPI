package dev.patika.core.config.mapper;

import dev.patika.dto.request.AppointmentRequest;
import dev.patika.dto.response.standard.AppointmentResponse;
import dev.patika.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper
public interface IAppointmentMapper {
    Appointment asEntity(AppointmentRequest request);

    AppointmentResponse asOutput(Appointment appointment);

    List<AppointmentResponse> asOutput(List<Appointment> appointmentList);


    void update(@MappingTarget Appointment entity, AppointmentRequest request);

}
