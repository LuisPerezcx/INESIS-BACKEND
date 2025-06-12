package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.FechasRegistradas;

import java.util.List;
import java.util.Map;

public interface IFechasRegistradasService {
    List<FechasRegistradas> findAll();

    FechasRegistradas findById(Long id);

    FechasRegistradas save(FechasRegistradas fechasRegistradas) throws Exception;

    FechasRegistradas create(Map<String, Object> params) throws Exception;

    FechasRegistradas update(FechasRegistradas fechasRegistradas, Map<String, Object> params) throws Exception;

    FechasRegistradas build(Map<String, Object> params, FechasRegistradas fechasRegistradas) throws IllegalArgumentException;

    FechasRegistradas updateInstance(FechasRegistradas fechasRegistradas) throws Exception;

    void deleteById(Long id);
}
