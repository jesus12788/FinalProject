package pet.shelter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.shelter.controller.model.LocationData;
import pet.shelter.service.ShelterService;

@RestController
@RequestMapping("/pet_shelter")
@Slf4j
public class ShelterController {
	@Autowired
	private ShelterService shelterService;
	
	@PostMapping("/location")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LocationData createLocation(LocationData locationData) {
		log.info("Creating location {}", locationData);
		return shelterService.savelocation(locationData);
	}
	

}
