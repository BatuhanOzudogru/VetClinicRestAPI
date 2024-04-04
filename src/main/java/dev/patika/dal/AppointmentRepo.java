package dev.patika.dal;

import dev.patika.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

       Optional<Appointment> findByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime dateTime);
       Optional<Appointment> findByDoctorAndAnimalAndAppointmentDate(Doctor doctor, Animal animal, LocalDateTime dateTime);
       Optional<List<Appointment>> findByDoctorAndAppointmentDateBetween(Doctor doctor, LocalDateTime startDate, LocalDateTime endDate);

       Optional<List<Appointment>> findByAnimalAndAppointmentDateBetween(Animal animal, LocalDateTime startDate, LocalDateTime endDate);


}
