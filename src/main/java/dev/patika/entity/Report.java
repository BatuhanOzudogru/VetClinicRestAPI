package dev.patika.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "report")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "title")
    @NotEmpty
    private String title;

    @Column(name = "diagnosis")
    @NotEmpty
    private String diagnosis;

    @Column(name = "price")
    @Positive
    private double price;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id" , referencedColumnName = "id")
    private Appointment appointment;

    @OneToMany(mappedBy = "report", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccineList;
}
