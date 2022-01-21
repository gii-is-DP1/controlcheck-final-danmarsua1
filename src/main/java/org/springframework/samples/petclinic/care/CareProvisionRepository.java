package org.springframework.samples.petclinic.care;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CareProvisionRepository extends CrudRepository<CareProvision, Integer>{
	
	@Query("SELECT careProvision FROM CareProvision careProvision")
    List<CareProvision> findAll();        
	
    Optional<CareProvision> findById(int id);
    CareProvision save(CareProvision p);
    
    @Query("SELECT care FROM Care care")
	List<Care> findAllCares();
    
    //@Query("SELECT care FROM Care care, PetType type WHERE care.compatiblePetTypes.petTypeId = type.id AND type.name = :petTypeName")
    //List<Care> findCompatibleCares(String petTypeName);
    
    @Query("SELECT care FROM Care care WHERE care.name = :name")
    Care findCareByName(String name);
    //List<CareProvision> findCaresProvidedByVisitId(Integer visitId);
}
