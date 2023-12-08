package dev.patika.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.patika.entity.Doctor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateRequest {

    @NotNull(message = "available date can't be empty")
    private LocalDate date;

    private Doctor doctor;

//    @NotEmpty(message = "doctor_id can't be empty")
//    @Positive(message = "doctor_id must be positive")
//    private Long doctor_id;
}
