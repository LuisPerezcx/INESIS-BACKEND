package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.Trabajo;

public interface ITrabajoService {
    List<Trabajo> findAll();
    Trabajo findById(Long id);
    Trabajo save(Trabajo trabajo) throws Exception;
    Trabajo create(Map<String, Object> params) throws Exception;
    Trabajo update(Trabajo trabajo, Map<String, Object> params) throws Exception;
    Trabajo build(Map<String, Object> params, Trabajo trabajo) throws IllegalArgumentException;
    Trabajo updateInstance(Trabajo trabajo) throws Exception;
    void deleteById(Long id);
}
