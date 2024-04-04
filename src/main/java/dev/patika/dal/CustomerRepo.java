package dev.patika.dal;

import dev.patika.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneOrMail(String phone, String mail);

    @Query("SELECT c FROM Customer c WHERE c.name ILIKE %:name%")
    Optional<List<Customer>> findByNameIgnoreCaseLike(@Param("name") String name);
    
}
