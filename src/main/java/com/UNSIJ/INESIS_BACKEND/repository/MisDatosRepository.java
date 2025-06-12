package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.MisDatos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MisDatosRepository extends JpaRepository<MisDatos, Long> {
    Optional<MisDatos> findByAlumno_Id(Long id);
}
