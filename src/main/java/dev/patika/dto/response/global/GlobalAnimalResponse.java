package dev.patika.dto.response.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalAnimalResponse {
    private Long id;
    private String name;
}
