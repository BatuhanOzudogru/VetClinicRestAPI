package dev.patika.dto.response.standard;

import dev.patika.dto.response.global.GlobalDoctorResponse;
import dev.patika.dto.response.global.GlobalAnimalResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private Long id;

    private LocalDateTime appointmentDate;

    private GlobalAnimalResponse animal;

    private GlobalDoctorResponse doctor;

}
