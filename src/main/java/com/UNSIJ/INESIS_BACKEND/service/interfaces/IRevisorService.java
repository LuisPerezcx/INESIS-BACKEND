package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.Revisor;

import java.util.List;
import java.util.Map;

public interface IRevisorService {
    List<Revisor> findAll();

    Revisor findById(Long id);

    Revisor save(Revisor revisor) throws Exception;

    Revisor create(Map<String, Object> params) throws Exception;

    Revisor update(Revisor revisor, Map<String, Object> params) throws Exception;

    Revisor build(Map<String, Object> params, Revisor revisor) throws IllegalArgumentException;

    void deleteById(Long id);
}
