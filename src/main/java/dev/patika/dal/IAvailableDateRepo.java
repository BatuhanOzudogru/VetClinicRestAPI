package dev.patika.dal;

import dev.patika.entity.AvailableDate;
import dev.patika.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {
   Optional<AvailableDate> findByDate(LocalDate date);

//   @Query("SELECT availableDate FROM AvailableDate JOIN Doctor on AvailableDate. ")
   // SELECT available_date FROM available_date WHERE doctor_id = ?
//   @Query("SELECT available_date FROM available_date WHERE doctor_id = ?1")
//   List<AvailableDateResponse> findByDoctorId(Long id, org.springframework.data.domain.Pageable pageable);

   Optional<AvailableDate> findByDoctorAndDate(Doctor doctor, LocalDate date);
}
