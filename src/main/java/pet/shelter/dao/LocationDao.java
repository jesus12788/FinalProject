package pet.shelter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.shelter.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long> {

}
