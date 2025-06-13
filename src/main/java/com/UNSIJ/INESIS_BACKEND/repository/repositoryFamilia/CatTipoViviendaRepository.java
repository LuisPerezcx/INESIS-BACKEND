package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatTipoVivienda;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CatTipoViviendaRepository extends JpaRepository<CatTipoVivienda, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a CatTipoVivienda by its ID:
    // Optional<CatTipoViviendaModel> findById(Long id);

    // You can also define methods for other operations as needed

}
