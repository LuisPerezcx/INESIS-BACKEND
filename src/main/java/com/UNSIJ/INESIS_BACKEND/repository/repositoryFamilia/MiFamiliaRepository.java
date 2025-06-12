package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiFamiliaRepository extends JpaRepository<MiFamilia, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a MiFamilia by its ID:
    // Optional<MiFamiliaModel> findById(Long id);

    // You can also define methods for other operations as needed

}
