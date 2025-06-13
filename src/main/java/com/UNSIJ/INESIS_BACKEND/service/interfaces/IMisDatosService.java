package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.MisDatos;

import java.util.List;
import java.util.Map;

public interface IMisDatosService {
    List<MisDatos> findAll();

    MisDatos findById(Long id);

    MisDatos findByAlumno(Long id);

    MisDatos save(MisDatos misDatos) throws Exception;

    MisDatos create(Map<String, Object> params) throws Exception;

    MisDatos update(MisDatos misDatos, Map<String, Object> params) throws Exception;

    MisDatos build(Map<String, Object> params, MisDatos misDatos) throws IllegalArgumentException;

    MisDatos updateInstance(MisDatos misDatos) throws Exception;

    void deleteById(Long id);
}
