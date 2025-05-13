package com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatInternetModel;

public interface CatInternetRepository extends JpaRepository<CatInternetModel, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find a CatInternet by its ID:
    // Optional<CatInternetModel> findById(Long id);
    
    // You can also define methods for other operations as needed
    
}
