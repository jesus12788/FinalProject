package pet.shelter.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import pet.shelter.entity.Dog;
import pet.shelter.entity.Foster;
import pet.shelter.entity.Location;

@Data
@NoArgsConstructor
public class LocationData {
	private Long locationId;
	
	private String businessName;
	
	private String streetAddress;
	
	private String city;
	
	private String state;
	
	private String zip;
	
    private String phone;
	
	private Set<FosterData> fosters = new HashSet<>();

	public LocationData(Location location) {
		
		this.locationId = location.getLocationId();
		this.businessName = location.getBusinessName();
		this.streetAddress = location.getStreetAddress();
		this.city = location.getCity();
		this.state = location.getState();
		this.zip = location.getZip();
		this.phone = location.getPhone();

		for (Foster foster : location.getFosters()) {
			this.fosters.add(new FosterData(foster));
		}
	}

	// For Test
	//
	public LocationData(Long locationId, String businessName, String streetAddress, String city, String state,
			String zip, String phone) {
		this.locationId = locationId;
		this.businessName = businessName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}

	// Also for Test
	public Location toLocation() {
		
		Location location = new Location();
		
		
		location.setLocationId(locationId);
	
		location.setBusinessName(businessName);
		
		location.setStreetAddress(streetAddress);
		
		location.setCity(city);
		
		location.setState(state);
		
		location.setZip(zip);
		
		location.setPhone(phone);

		for (FosterData fosterData : fosters) {
			location.getFosters().add(fosterData.toFoster());
		}
		return location;
	}

	@Data
	@NoArgsConstructor
	static public class FosterData {
		private Long fosterId;
		
		private String firstName;
		
		private String lastName;
		
		private String fosterAddress;
		
		private String fosterPhone;
		
		private String email;
		
		private Set<DogData> dogs = new HashSet<>();
		
		private Set<LocationData> location = new HashSet<>();

		public FosterData(Foster foster) {
			this.fosterId = foster.getFosterId();
			this.firstName = foster.getFirstName();
			this.lastName = foster.getLastName();
			this.fosterAddress = foster.getFosterAddress();
			this.fosterPhone = foster.getFosterPhone();
			this.email = foster.getEmail();

			for (Dog dog : foster.getDogs()) {
				this.dogs.add(new DogData(dog));
			}
//			for (Location location : foster.getLocation()) {
//				this.location.add(new LocationData(location));
//			}
		}

		public Foster toFoster() {
			Foster foster = new Foster();

			
			foster.setFosterId(fosterId);
			foster.setFirstName(firstName);
			foster.setLastName(lastName);
			foster.setFosterAddress(fosterAddress);
			foster.setFosterPhone(fosterPhone);
			foster.setEmail(email);

			for (DogData dogData : dogs) {
				foster.getDogs().add(dogData.toDog());
			}
			for (LocationData locationData : location) {
				foster.getLocation().add(locationData.toLocation());
			}

			return foster;
		}
	}

	@Data
	@NoArgsConstructor
	static public class DogData {
		private Long dogId;
		private String age;
		private String name;
		private String color;
		private String breed;

		public DogData(Dog dog) {
			this.dogId = dog.getDogId();
			this.age = dog.getAge();
			this.name = dog.getName();
			this.color = dog.getColor();
			this.breed = dog.getBreed();

		}

		public Dog toDog() {
			Dog dog = new Dog();

			dog.setDogId(dogId);
			dog.setAge(age);
			dog.setName(name);
			dog.setColor(color);
			dog.setBreed(breed);

			return dog;
		}

	}
}
