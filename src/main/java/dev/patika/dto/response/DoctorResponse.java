package dev.patika.dto.response;

import dev.patika.entity.Appointment;
import dev.patika.entity.AvailableDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
public class DoctorResponse {

    private Long id;

    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;

//    private List<Appointment> appointmentList;
//
    private List<AvailableDateDoctorResponse> availableDateList;
//    private AvailableDateDoctorResponse availableDateDoctorResponse;
}
