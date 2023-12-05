package dev.patika.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @Email
    private String mail;

    private String address;

    private String city;
}
