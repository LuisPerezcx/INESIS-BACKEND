package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.Ocupacion;

import java.util.List;
import java.util.Map;

public interface IOcupacionService {
    List<Ocupacion> findAll();
    Ocupacion findById(Long id);
    Ocupacion save(Ocupacion ocupacion) throws Exception;
    Ocupacion create(Map<String, Object> params) throws Exception;
    Ocupacion update(Ocupacion ocupacion, Map<String, Object> params) throws Exception;
    Ocupacion build(Map<String, Object> params, Ocupacion ocupacion) throws IllegalArgumentException;
    Ocupacion updateInstance(Ocupacion ocupacion) throws Exception;
    void deleteById(Long id);
}
