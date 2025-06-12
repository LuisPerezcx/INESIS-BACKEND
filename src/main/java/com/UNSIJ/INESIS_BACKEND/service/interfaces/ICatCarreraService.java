package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.CatCarrera;

import java.util.List;
import java.util.Map;

public interface ICatCarreraService {
    List<CatCarrera> findAll();

    CatCarrera findById(Long id);

    CatCarrera save(CatCarrera catCarrera) throws Exception;

    CatCarrera create(Map<String, Object> params) throws Exception;

    CatCarrera update(CatCarrera catCarrera, Map<String, Object> params) throws Exception;

    CatCarrera build(Map<String, Object> params, CatCarrera catCarrera) throws IllegalArgumentException;

    CatCarrera updateInstance(CatCarrera catCarrera) throws Exception;

    void deleteById(Long id);

}
