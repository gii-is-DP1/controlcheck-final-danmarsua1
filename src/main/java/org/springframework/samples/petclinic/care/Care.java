package org.springframework.samples.petclinic.care;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cares")
public class Care {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
	
	@Size(min = 5, max = 50)
	@Column(name = "name", unique = true)
	@NotEmpty
    String name;
	
	@NotEmpty
    String description;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "compatible_pet_types", joinColumns = @JoinColumn(name = "care_id"),
			inverseJoinColumns = @JoinColumn(name = "pet_type_id"))
	@NotEmpty
    Set<PetType> compatiblePetTypes;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "incompatible_cares", joinColumns = @JoinColumn(name = "compatible_care_id"),
			inverseJoinColumns = @JoinColumn(name = "not_compatible_care_id"))
    Set<Care> incompatibleCares;
}
