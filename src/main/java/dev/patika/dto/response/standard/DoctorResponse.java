package dev.patika.dto.response.standard;

import dev.patika.dto.response.global.GlobalAvailableDateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private List<GlobalAvailableDateResponse> availableDateList;

}
