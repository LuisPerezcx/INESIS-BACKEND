package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.CodigoPostal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatCodigoPostalRepository extends JpaRepository<CodigoPostal, Long> {
    List<CodigoPostal> findCodigoPostalByCodigoPostal(String codigoPostal);
}