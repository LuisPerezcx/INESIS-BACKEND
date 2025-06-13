package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ServiciosVivienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiciosViviendaRepository extends JpaRepository<ServiciosVivienda, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a ServiciosVivienda by its ID:
    // Optional<ServiciosViviendaModel> findById(Long id);

    // You can also define methods for other operations as needed

}
