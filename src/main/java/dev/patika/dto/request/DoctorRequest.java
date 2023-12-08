package dev.patika.dto.request;

import dev.patika.entity.AvailableDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {
    @NotEmpty(message = "name value can't be empty")
    private String name;
    @NotEmpty(message = "phone value can't be empty")
    private String phone;

    @Email
    @NotEmpty(message = "mail value can't be empty")
    private String mail;

    private String address;

    private String city;


}
