package dev.patika.dto.request;

import dev.patika.entity.Animal;
import dev.patika.entity.Customer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineRequest {
    @NotEmpty(message = "name value can't be empty")
    private String name;

    @NotEmpty(message = "code value can't be empty")
    private String code;

    private LocalDate protectionStartDate;

    private LocalDate protectionFinishDate;

    private Animal animal;


}
