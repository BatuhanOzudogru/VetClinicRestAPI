package dev.patika.dto.request;

import dev.patika.entity.Appointment;
import dev.patika.entity.Vaccine;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {
    @NotEmpty(message = "title value can't be empty")
    private String title;
    @NotEmpty(message = "diagnosis value can't be empty")
    private String diagnosis;
    @NotEmpty(message = "prescription value can't be empty")
    private double price;
    @NotEmpty(message = "appointment value can't be empty")
    private Appointment appointment;


}
