package dev.patika.dal;

import dev.patika.entity.Animal;
import dev.patika.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {

    Optional<List<Vaccine>> findByAnimal(Animal animal);

    @Query("SELECT v FROM Vaccine v WHERE v.code = :vaccineCode " +
            "AND v.animal.id = :animalId " +
            "AND v.protectionFinishDate > :startDate")
    List<Vaccine> findVaccinesAfterStartDate(
            @Param("vaccineCode") String vaccineCode,
            @Param("animalId") Long animalId,
            @Param("startDate") LocalDate startDate
    );

    @Query("SELECT v FROM Vaccine v WHERE v.protectionStartDate >= :startDate " +
            "AND v.protectionFinishDate <= :endDate")
    List<Vaccine> findByProtectionFinishDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
