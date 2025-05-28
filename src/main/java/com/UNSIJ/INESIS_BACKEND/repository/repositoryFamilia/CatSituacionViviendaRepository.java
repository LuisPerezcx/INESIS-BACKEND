package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;
import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatSituacionViviendaModel;

public interface CatSituacionViviendaRepository extends JpaRepository<CatSituacionViviendaModel, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a CatSituacionVivienda by its ID:
    // Optional<CatSituacionViviendaModel> findById(Long id);
    
    // You can also define methods for other operations as needed
    
}
