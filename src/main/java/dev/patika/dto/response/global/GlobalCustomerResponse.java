package dev.patika.dto.response.global;

import dev.patika.entity.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalCustomerResponse {

    private Long id;

    private String name;

}
