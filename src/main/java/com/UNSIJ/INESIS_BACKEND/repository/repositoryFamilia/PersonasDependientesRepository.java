package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.PersonasDependientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonasDependientesRepository extends JpaRepository<PersonasDependientes, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a HermanosDependientes by its ID:
    // Optional<HermanosDependientesModel> findById(Long id);

    // You can also define methods for other operations as needed   

}
