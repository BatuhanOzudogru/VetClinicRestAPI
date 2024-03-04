package dev.patika.dto.response.standard;

import dev.patika.dto.response.global.GlobalVaccineResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {

    private Long id;
    private String title;
    private String diagnosis;
    private double price;
    private AppointmentResponse appointment;
    private List<GlobalVaccineResponse> vaccineList;


}
