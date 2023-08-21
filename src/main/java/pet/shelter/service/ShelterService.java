package pet.shelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.shelter.controller.model.LocationData;
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
	
	

}
