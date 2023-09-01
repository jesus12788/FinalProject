package pet.shelter.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/*
	 * location - Post, Get, Put and Delete.
	 */
	
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
	 * foster_location
	 */
	@PostMapping("/foster")
	@ResponseStatus(code = HttpStatus.CREATED)
	public FosterData createFoster(@RequestParam(name = "locationId")Long locationId, @RequestBody FosterData fosterData) {
		log.info("Creating foster {}", fosterData);
		LocationData locationData = shelterService.findlocation(locationId);
		Set<LocationData> locations = new HashSet<>();
		locations.add(locationData);
		fosterData.setLocation(locations);
		return shelterService.savefoster(fosterData);
		
		
	
	}
	/*
	 * foster
	 */
	
	@GetMapping("/foster/{fosterId}")
	public FosterData retrieveFoster(@PathVariable Long fosterId) {
		log.info("Retrieving foster with ID={}", fosterId);
		return shelterService.retrieveFosterById(fosterId);
	}
	
	/*
	 * Dog
	 */
	
	
	@PostMapping("/{fosterId}/dog")
	@ResponseStatus(code = HttpStatus.CREATED)
	public DogData insertDog(@PathVariable Long fosterId,
			@RequestBody DogData dogData) {
		
		log.info("Creating dog {} for foster with ID={}", dogData,
				fosterId);
		
		return shelterService.savedog(fosterId, dogData);
	}
	
	@GetMapping("/foster")
	public List<FosterData> retrieveAllFosters() {
		log.info("Retrieve all fosters called.");
		return shelterService.retrieveAllFosters();
	}
	

//	@PostMapping("/location/{locationId}/foster")
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public FosterData insertFoster(@PathVariable Long locationId,
//			@RequestBody FosterData fosterData) {
//		
//		log.info("Creating foster {} for location with ID={}", fosterData,
//				locationId);
//		
//		return shelterService.saveFoster(locationId, fosterData);
//	}
	
}
