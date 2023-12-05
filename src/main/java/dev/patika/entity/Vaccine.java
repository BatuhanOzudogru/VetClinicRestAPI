package dev.patika.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccine")
@Data
@RequiredArgsConstructor // Bunu dene!!!
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "code")
    private String code;

    @NotNull
    @Temporal(TemporalType.DATE) ///
    @Column(name = "protection_start_date")
    private LocalDate protectionStartDate;

    @NotNull
    @Temporal(TemporalType.DATE) ///
    @Column(name = "protection_finish_date")
    private LocalDate protectionFinishDate;
}
