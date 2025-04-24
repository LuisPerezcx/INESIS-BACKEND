package com.UNSIJ.INESIS_BACKEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UNSIJ.INESIS_BACKEND.model.MedioTraslado;

import java.util.List;

public interface MediosTrasladoRepository extends JpaRepository<MedioTraslado, Long> {
    List<MedioTraslado> findByMisDatosId(Long idMisDatos);
}
