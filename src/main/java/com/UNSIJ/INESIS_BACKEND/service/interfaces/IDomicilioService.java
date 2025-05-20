package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.Domicilio;

public interface IDomicilioService {
    List<Domicilio> findAll();
    Domicilio findById(Long id);
    Domicilio save(Domicilio domicilio) throws Exception;
    Domicilio create(Map<String, Object> params) throws Exception;
    Domicilio update(Domicilio domicilio, Map<String, Object> params) throws Exception;
    Domicilio build(Map<String, Object> params, Domicilio domicilio) throws IllegalArgumentException;
    Domicilio updateInstance(Domicilio domicilio) throws Exception;
    void deleteById(Long id);
}
