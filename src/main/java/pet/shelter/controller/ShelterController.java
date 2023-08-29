package pet.shelter.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.shelter.controller.model.LocationData;
import pet.shelter.controller.model.LocationData.DogData;
import pet.shelter.controller.model.LocationData.FosterData;
import pet.shelter.service.ShelterService;


@RestController
@RequestMapping("/pet_shelter")
@Slf4j
public class ShelterController {
	@Autowired
	private ShelterService shelterService;
	
	@PostMapping("/location")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LocationData createLocation(@RequestBody LocationData locationData) {
		log.info("Creating location {}", locationData);
		return shelterService.savelocation(locationData);
	}
	
	@GetMapping("/location")
	public List<LocationData> retrieveAllLocations() {
		log.info("Retrieving all location");
		return shelterService.retrieveAllLocations();
	
	}
	
	@PutMapping("/location/{locationId}")
	public LocationData updateLocation(@PathVariable Long locationId,
			@RequestBody LocationData locationData) {
		locationData.setLocationId(locationId);
		log.info("Updating location {}", locationData);
		return shelterService.savelocation(locationData);
	}
	
	@DeleteMapping("/location/{locationId}")
	public Map<String, String> deleteLocation(@PathVariable Long locationId) {
		log.info("Deleting location with ID=" + locationId + ".");
		shelterService.deleteLocation(locationId);
		
		return Map.of("message", "Location with ID=" + locationId
				+ " was deleted successfully.");
	}
	/*
	 * foster
	 */
	@PostMapping("/foster")
	@ResponseStatus(code = HttpStatus.CREATED)
	public FosterData createFoster(@RequestBody FosterData fosterData) {
		log.info("Creating foster {}", fosterData);
		return shelterService.savefoster(fosterData);
		
		
	
	}
	/*
	 * Dog
	 */
//	@PostMapping("/dog")
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public DogData createDog(@RequestBody DogData dogData) {
//		log.info("Creating dog {}", dogData);
//		return shelterService.savedog(dogData);
//	}
	@PostMapping("/{fosterId}/dog")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DogData insertDog(@PathVariable Long fosterId,
			@RequestBody DogData dogData) {
		log.info("Creating dog {} for foster with ID={}", dogData,
				fosterId);
		return shelterService.savedog(fosterId, dogData);
	}	
}
