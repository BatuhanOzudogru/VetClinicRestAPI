package dev.patika.dal;

import dev.patika.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{
    @Modifying
    @Query("DELETE FROM Report r WHERE r.appointment.id = :id")
    void deleteByAppointmentId(@Param("id") Long id);

    @Query("SELECT r FROM Report r WHERE r.appointment.id = :id")
    Optional<Report> findReportByAppointmentId(@Param("id") Long id);

}
