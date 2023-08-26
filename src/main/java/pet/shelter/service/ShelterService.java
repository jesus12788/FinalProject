package pet.shelter.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.shelter.controller.model.LocationData;
import pet.shelter.controller.model.LocationData.DogData;
import pet.shelter.dao.LocationDao;
import pet.shelter.entity.Location;

@Service
public class ShelterService {

	@Autowired
	private LocationDao locationDao;

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
	

}
