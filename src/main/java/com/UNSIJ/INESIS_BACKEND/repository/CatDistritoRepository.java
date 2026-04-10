package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.CatDistrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatDistritoRepository extends JpaRepository<CatDistrito, Long> {
    List<CatDistrito> findByRegion_Id(Long idCatRegion);
}
