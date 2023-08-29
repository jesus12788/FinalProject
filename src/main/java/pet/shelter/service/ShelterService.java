package pet.shelter.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.shelter.controller.model.LocationData;
import pet.shelter.controller.model.LocationData.DogData;
import pet.shelter.controller.model.LocationData.FosterData;
import pet.shelter.dao.DogDao;
import pet.shelter.dao.FosterDao;
import pet.shelter.dao.LocationDao;
import pet.shelter.entity.Dog;
import pet.shelter.entity.Foster;
import pet.shelter.entity.Location;



@Service
public class ShelterService {

	@Autowired
	private LocationDao locationDao;
	
	@Autowired
	private FosterDao fosterDao;
	
	@Autowired
	private DogDao dogDao;
	
	

	@Transactional(readOnly = false)
	public LocationData savelocation(LocationData locationData) {
		Location location = locationData.toLocation();
		Location dbLocation = locationDao.save(location);

		return new LocationData(dbLocation);

	}
	@Transactional(readOnly = true)
	public List<LocationData> retrieveAllLocations() {
		List<Location> locationEntities = locationDao.findAll();
		List<LocationData> locationDtos = new LinkedList<>();	
		
//		//@formatter:off
//        return locationDao.findAll()
//	    .stream()
//	    .map(LocationData::new)
//	    .toList();
//        //@formatter: on
		
		 // @formatter: off
		   return locationDao.findAll()
			.stream()
			.sorted((loc1, loc2) -> loc1.getBusinessName().compareTo(loc2.getBusinessName()))
			.map(LocationData::new)
			.toList();
		// @formatter: on
		   
	
    }
	private Location findLocationById(Long locationId) {
		return locationDao.findById(locationId)
				.orElseThrow(() -> new NoSuchElementException(
						"Location with ID=" + locationId + " was not found."));
	}
	
	@Transactional(readOnly = false)
	public void deleteLocation(Long locationId) {
		Location location = findLocationById(locationId);
		locationDao.delete(location);
	}
	/*
	 * Start of foster.
	 */
	@Transactional(readOnly = false)
	public FosterData savefoster(FosterData fosterData) {
		Foster foster = fosterData.toFoster();
		Foster dbfoster = fosterDao.save(foster);
		
		return new FosterData(dbfoster);
	}
	/*
	 * Start of dog.
	 */
//	@Transactional(readOnly = false)
//	public DogData savedog(Long fosterId, DogData dogData) {
//		Dog dog = dogData.toDog();
//		Dog dbdog = dogDao.save(dog);
//		
//		return new DogData(dbdog);
//	}
	@Transactional(readOnly = false)
	public DogData savedog(Long fosterId, DogData dogData) {
//		Dog dog = findOrCreateDog(fosterId, fosterId);
		Dog dog = dogData.toDog();
		Dog dbDog = dogDao.save(dog);
		return new DogData(dbDog);
		
		
	}
//	private Dog findOrCreateDog(Long fosterId, Long dogId) {
//		if(Objects.isNull(fosterId)) {
//			return new Dog();
//		}
//		else {
//			return findDogById(fosterId, dogId);
//		}
//	}
//	private Dog findDogById(Long fosterId, Long dogId) {
//		
//		return null;
//	}
//	
	
	

}
