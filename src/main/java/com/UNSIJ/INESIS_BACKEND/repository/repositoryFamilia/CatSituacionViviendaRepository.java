package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatSituacionVivienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatSituacionViviendaRepository extends JpaRepository<CatSituacionVivienda, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a CatSituacionVivienda by its ID:
    // Optional<CatSituacionViviendaModel> findById(Long id);

    // You can also define methods for other operations as needed

}
