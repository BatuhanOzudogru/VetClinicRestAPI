package dev.patika.dto.response.standard;

import dev.patika.dto.response.global.GlobalCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private Long id;

    private String name;

    private String species;

    private String breed;

    private String gender;

    private String color;

    private LocalDate dateOfBirth;

    private GlobalCustomerResponse customer;


}
