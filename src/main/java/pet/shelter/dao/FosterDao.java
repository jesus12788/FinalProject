package pet.shelter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.shelter.entity.Foster;

public interface FosterDao extends JpaRepository<Foster, Long> {

}
