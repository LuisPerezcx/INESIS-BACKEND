package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.BienesHogar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BienesHogarRepository extends JpaRepository<BienesHogar, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a BienesHogar by its ID:
    // Optional<BienesHogarModel> findById(Long id);

    // You can also define methods for other operations as needed

}
