package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MediosEstudioModel;

public interface MediosEstudioRepository extends JpaRepository<MediosEstudioModel, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a MediosEstudio by its ID:
    // Optional<MediosEstudioModel> findById(Long id);
    
    // You can also define methods for other operations as needed
    
}
