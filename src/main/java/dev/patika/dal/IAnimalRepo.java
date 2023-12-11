package dev.patika.dal;

import dev.patika.entity.Animal;
import dev.patika.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal, Long> {
   Optional<Animal> findByNameAndSpeciesAndBreedAndDateOfBirth(String name, String species, String breed, LocalDate date);
   Optional<Animal> findByNameAndCustomer(String name, Customer customer);
   Optional<Animal> findByName(String name);

}
