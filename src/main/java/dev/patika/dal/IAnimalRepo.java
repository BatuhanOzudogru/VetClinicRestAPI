package dev.patika.dal;

import dev.patika.entity.Animal;
import dev.patika.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal, Long> {
   Optional<Animal> findByNameAndSpeciesAndBreedAndDateOfBirth(String name, String species, String breed, LocalDate date);
   Optional<Animal> findByNameAndCustomer(String name, Customer customer);

   @Query("SELECT a FROM Animal a WHERE a.name ILIKE %:name%")
   Optional<List<Animal>> findByNameIgnoreCaseLike(@Param("name") String name);

   @Query("SELECT a FROM Animal a WHERE a.customer.name ILIKE %:name%")
   Optional<List<Animal>> findByCustomerNameIgnoreCaseLike(@Param("name") String name);

}
