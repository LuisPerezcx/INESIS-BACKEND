package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatBienesHogar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatBienesHogarRepository extends JpaRepository<CatBienesHogar, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a CatBienesHogar by its ID:
    // Optional<CatBienesHogarModel> findById(Long id);

    // You can also define methods for other operations as needed

}
