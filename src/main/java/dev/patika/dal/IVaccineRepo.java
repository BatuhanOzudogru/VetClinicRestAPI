package dev.patika.dal;

import dev.patika.entity.Animal;
import dev.patika.entity.Customer;
import dev.patika.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IVaccineRepo extends JpaRepository<Vaccine, Long> {

    @Query("SELECT v FROM Vaccine v WHERE v.code = :vaccineCode " +
            "AND v.animal.id = :animalId " +
            "AND v.protectionFinishDate > :startDate")
    List<Vaccine> findVaccinesAfterStartDate(
            @Param("vaccineCode") String vaccineCode,
            @Param("animalId") Long animalId,
            @Param("startDate") LocalDate startDate
    );



}
