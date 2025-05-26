package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;
import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatBienesHogarModel;

public interface CatBienesHogarRepository extends JpaRepository<CatBienesHogarModel, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a CatBienesHogar by its ID:
    // Optional<CatBienesHogarModel> findById(Long id);
    
    // You can also define methods for other operations as needed
    
}
