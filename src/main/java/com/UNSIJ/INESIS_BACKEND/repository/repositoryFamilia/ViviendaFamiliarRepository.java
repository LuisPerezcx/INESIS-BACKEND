package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViviendaFamiliarRepository extends JpaRepository<ViviendaFamiliar, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find a ViviendaFamiliar by its ID:
    // Optional<ViviendaFamiliarModel> findById(Long id);

    // You can also define methods for other operations as needed

}
