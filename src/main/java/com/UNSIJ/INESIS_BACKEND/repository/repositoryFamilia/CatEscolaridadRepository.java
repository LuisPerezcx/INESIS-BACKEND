package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatEscolaridadModel;


public interface CatEscolaridadRepository extends JpaRepository<CatEscolaridadModel, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a CatEscolaridad by its ID:
    // Optional<CatEscolaridadModel> findById(Long id);
    
    // You can also define methods for other operations as needed
    
}
