package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.MedioTraslado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediosTrasladoRepository extends JpaRepository<MedioTraslado, Long> {
    List<MedioTraslado> findByMisDatosId(Long idMisDatos);
}
