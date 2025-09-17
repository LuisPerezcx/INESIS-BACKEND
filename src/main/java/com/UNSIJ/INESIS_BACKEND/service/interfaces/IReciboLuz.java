package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.model.ReciboLuz;

public interface IReciboLuz {
    List<ReciboLuz> findAll();
    ReciboLuz findById(Long id);
    ReciboLuz save(ReciboLuz ReciboLuzModel) throws Exception;
    ReciboLuz create(Map<String, Object> params, Alumno alumno) throws Exception;
    ReciboLuz update(ReciboLuz ReciboLuzModel, Map<String, Object> params, Alumno alumno) throws Exception;
    ReciboLuz build(Map<String, Object> params, ReciboLuz ReciboLuzModel, Alumno alumno) throws IllegalArgumentException;
    void deleteById(Long id);
}
