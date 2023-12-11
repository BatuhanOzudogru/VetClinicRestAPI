package dev.patika.dto.request;

import dev.patika.entity.Animal;
import dev.patika.entity.Doctor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    @NotNull(message = "appointment date value can't be empty")
    private LocalDateTime appointmentDate;

    private Animal animal;

    private Doctor doctor;


}
