package pet.shelter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.shelter.entity.Dog;

public interface DogDao extends JpaRepository<Dog, Long> {

}
