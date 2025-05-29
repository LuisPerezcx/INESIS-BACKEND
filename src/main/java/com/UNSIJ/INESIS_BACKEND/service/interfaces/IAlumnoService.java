package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;

import java.util.List;
import java.util.Map;

public interface IAlumnoService {
    List<Alumno> findAll();
    Alumno findById(Long id);
    Alumno save(Alumno alumnoModel) throws Exception;
    Alumno create(Map<String, Object> params) throws Exception;
    Alumno update(Alumno alumnoModel, Map<String, Object> params) throws Exception;
    Alumno build(Map<String, Object> params, Alumno alumnoModel) throws IllegalArgumentException;
    void deleteById(Long id);
}
