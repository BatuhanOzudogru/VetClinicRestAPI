package dev.patika.dto.response;

import dev.patika.entity.Appointment;
import dev.patika.entity.Customer;
import dev.patika.entity.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    private Customer customer;

    private List<Appointment> appointmentList;

    private List<Vaccine> vaccineList;

}
