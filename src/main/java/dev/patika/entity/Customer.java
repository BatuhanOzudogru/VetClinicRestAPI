package dev.patika.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "phone")
    private String phone;

    @Email
    @NotEmpty
    @Column(name = "mail")
    private String mail;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Animal> animalList;

}
