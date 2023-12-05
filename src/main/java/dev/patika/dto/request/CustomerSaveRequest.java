package dev.patika.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {

    private String name;


    private String phone;

    @Email
    private String mail;

    private String address;

    private String city;
}
