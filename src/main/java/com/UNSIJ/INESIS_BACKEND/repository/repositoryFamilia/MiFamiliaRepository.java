package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamiliaModel;

public interface MiFamiliaRepository extends JpaRepository<MiFamiliaModel, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a MiFamilia by its ID:
    // Optional<MiFamiliaModel> findById(Long id);
    
    // You can also define methods for other operations as needed
    
}
