package dev.patika.dal;

import dev.patika.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneOrMail(String phone, String mail);

  //  @Query("SELECT * FROM customer WHERE name = ?")
    Optional<Customer> findByName(String name);
}
