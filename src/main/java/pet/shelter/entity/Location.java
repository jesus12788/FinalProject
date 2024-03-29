package pet.shelter.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	private String businessName;
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	private String streetAddress;
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	private String city;
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	private String state;
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	private String zip;
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	private String phone;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "foster_location", joinColumns = @JoinColumn(name = "foster_id"),
	inverseJoinColumns = @JoinColumn(name = "location_id"))
	private Set<Foster> fosters = new HashSet<>();

}
