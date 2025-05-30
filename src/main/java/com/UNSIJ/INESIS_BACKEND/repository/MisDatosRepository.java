package com.UNSIJ.INESIS_BACKEND.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.UNSIJ.INESIS_BACKEND.model.MisDatos;

import java.util.Optional;

public interface MisDatosRepository extends JpaRepository<MisDatos,Long>{
    Optional<MisDatos> findByAlumno_Id(Long id);
}
