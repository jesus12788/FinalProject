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
	@ToString.Exclude
	private String firstName;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private String lastName;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private String fosterAddress;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private String fosterPhone;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private String email;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "foster", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set <Dog> dogs = new HashSet<>();
	
	//On line 45 PERSIST was changed to MERGE. It tells hibernate to merge the class and and insert the record.
	//org.hibernate.persistentobjectexception: detached entity passed to persist
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "foster_location", joinColumns = @JoinColumn(name = "foster_id"),
	inverseJoinColumns = @JoinColumn(name = "location_id"))
	private Set <Location> location = new HashSet<>();
	

}
