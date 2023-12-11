package dev.patika.dal;

import dev.patika.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {
//   Optional<Animal> findByNameAndSpeciesAndBreedAndDateOfBirth(String name, String species, String breed, LocalDate date);
//   Optional<Animal> findByNameAndCustomer(String name, Customer customer);
//   Optional<Animal> findByName(String name);

//    @Query("SELECT a FROM Appointment v WHERE a.doctor.id = :doctorId " +
//            "AND a.appointmentDate = :appointmentDate ")
//    Appointment findAppointmentDay(
//            @Param("doctorId") Long doctorId,
//            @Param("appointmentDate") LocalDateTime appointmentDate
//    );

       Optional<Appointment> findByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime dateTime);
       Optional<Appointment> findByDoctorAndAnimalAndAppointmentDate(Doctor doctor, Animal animal, LocalDateTime dateTime);
       Optional<List<Appointment>> findByDoctorAndAppointmentDateBetween(Doctor doctor, LocalDateTime startDate, LocalDateTime endDate);
       Optional<List<Appointment>> findByAnimalAndAppointmentDateBetween(Animal animal, LocalDateTime startDate, LocalDateTime endDate);

}
