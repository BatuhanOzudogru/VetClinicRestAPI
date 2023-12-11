package dev.patika.dto.request;

import dev.patika.entity.Customer;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
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
public class AnimalRequest {
    @NotEmpty(message = "name value can't be empty")
    private String name;

    @NotEmpty(message = "species value can't be empty")
    private String species;

    @NotEmpty(message = "breed value can't be empty")
    private String breed;

    private String gender;

    private String color;

    private LocalDate dateOfBirth;

    private Customer customer;


}
