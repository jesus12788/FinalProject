package pet.shelter.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long locationId;
	
	private String businessName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "location")
	private Set<Foster> fosters = new HashSet<>();

}
