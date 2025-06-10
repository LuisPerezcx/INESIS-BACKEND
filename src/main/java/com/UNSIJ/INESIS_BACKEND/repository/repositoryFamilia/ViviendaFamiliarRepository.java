package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;
import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliarModel;

public interface ViviendaFamiliarRepository  extends JpaRepository<ViviendaFamiliarModel, Long> {
    
    // Custom query methods can be defined here if needed
    // For example, to find a ViviendaFamiliar by its ID:
    // Optional<ViviendaFamiliarModel> findById(Long id);
    
    // You can also define methods for other operations as needed
    
}
