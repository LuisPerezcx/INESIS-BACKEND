package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.Transporte;

import java.util.List;
import java.util.Map;

public interface ITransporteService {
    List<Transporte> findAll();

    Transporte findById(Long id);

    Transporte save(Transporte transporte) throws Exception;

    Transporte create(Map<String, Object> params) throws Exception;

    Transporte update(Transporte transporte, Map<String, Object> params) throws Exception;

    Transporte build(Map<String, Object> params, Transporte transporte) throws IllegalArgumentException;

    Transporte updateInstance(Transporte transporte) throws Exception;

    void deleteById(Long id);
}
