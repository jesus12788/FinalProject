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
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Foster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fosterId;
	
	@EqualsAndHashCode.Exclude
	private String firstName;
	@EqualsAndHashCode.Exclude
	private String lastName;
	@EqualsAndHashCode.Exclude
	private String fosterAddress;
	@EqualsAndHashCode.Exclude
	private String fosterPhone;
	@EqualsAndHashCode.Exclude
	private String email;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "foster", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set <Dog> dogs = new HashSet<>();
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "location_foster", joinColumns = @JoinColumn(name = "foster_id"),
	inverseJoinColumns = @JoinColumn(name = "location_id"))
	private Set <Location> location = new HashSet<>();
	

}
