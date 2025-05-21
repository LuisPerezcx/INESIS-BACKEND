package com.UNSIJ.INESIS_BACKEND.repository;
import com.UNSIJ.INESIS_BACKEND.model.GastoFamiliarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoFamiliarRepository extends JpaRepository<GastoFamiliarModel, Long> {
}

