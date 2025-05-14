package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.AlumnoModel;

import java.util.List;
import java.util.Map;

public interface IAlumnoService {
    List<AlumnoModel> findAll();
    AlumnoModel findById(Long id);
    AlumnoModel save(AlumnoModel alumnoModel) throws Exception;
    AlumnoModel create(Map<String, Object> params) throws Exception;
    AlumnoModel update(AlumnoModel alumnoModel, Map<String, Object> params) throws Exception;
    AlumnoModel build(Map<String, Object> params, AlumnoModel alumnoModel) throws IllegalArgumentException;
    void deleteById(Long id);
}
