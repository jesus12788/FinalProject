package pet.shelter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Dog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dogId;
	
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	private String age;
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	private String name;
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	private String color;
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	private String breed;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "foster_id", nullable = false)
	private Foster foster;
	
	

}
