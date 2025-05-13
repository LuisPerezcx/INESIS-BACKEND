package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;
import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMaterialViviendaModel;

public interface CatMaterialViviendaRepository extends JpaRepository<CatMaterialViviendaModel, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a CatMaterialVivienda by its ID:
    // Optional<CatMaterialViviendaModel> findById(Long id);
    
    // You can also define methods for other operations as needed
    
}
