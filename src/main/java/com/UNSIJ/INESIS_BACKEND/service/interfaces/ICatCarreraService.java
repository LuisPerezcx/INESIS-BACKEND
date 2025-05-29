package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatCarrera;

public interface ICatCarreraService {
    List<CatCarrera> findAll();
    CatCarrera findById(Long id);
    CatCarrera save(CatCarrera catCarreraModel) throws Exception;
    CatCarrera create(Map<String, Object> params) throws Exception;
    CatCarrera update(CatCarrera catCarreraModel, Map<String, Object> params) throws Exception;
    CatCarrera build(Map<String, Object> params, CatCarrera catCarreraModel) throws IllegalArgumentException;
    CatCarrera updateInstance(CatCarrera catCarreraModel) throws Exception;
    void deleteById(Long id);
    
}
