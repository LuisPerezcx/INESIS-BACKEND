package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatEscolaridad;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CatEscolaridadRepository extends JpaRepository<CatEscolaridad, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a CatEscolaridad by its ID:
    // Optional<CatEscolaridadModel> findById(Long id);

    // You can also define methods for other operations as needed

}
