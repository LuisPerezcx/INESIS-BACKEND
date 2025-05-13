package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;
import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMediosEstudioModel;

public interface CatMediosEstudioRepository extends JpaRepository<CatMediosEstudioModel, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a CatMediosEstudio by its ID:
    // Optional<CatMediosEstudioModel> findById(Long id);
    
    // You can also define methods for other operations as needed
    
}
