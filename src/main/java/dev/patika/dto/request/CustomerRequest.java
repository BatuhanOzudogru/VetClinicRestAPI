package dev.patika.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @NotNull(message = "name value can't be null")
    private String name;
    @NotNull(message = "phone value can't be null")
    private String phone;

    @Email
    private String mail;

    private String address;

    private String city;
}
