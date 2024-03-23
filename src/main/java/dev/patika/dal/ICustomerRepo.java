package dev.patika.dal;

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
public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneOrMail(String phone, String mail);

    @Query("SELECT c FROM Customer c WHERE c.name ILIKE %:name%")
    Optional<List<Customer>> findByNameIgnoreCaseLike(@Param("name") String name);
    
}
