package dev.patika.dal;

import dev.patika.entity.Customer;
import dev.patika.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByPhoneOrMail(String phone, String mail);
}
