package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;
import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.PersonasDependientesModel;

public interface PersonasDependientesRepository extends JpaRepository<PersonasDependientesModel, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a HermanosDependientes by its ID:
    // Optional<HermanosDependientesModel> findById(Long id);
    
    // You can also define methods for other operations as needed   
    
}
