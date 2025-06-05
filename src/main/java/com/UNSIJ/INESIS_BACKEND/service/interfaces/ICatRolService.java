package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatRol;

public interface ICatRolService {
    List<CatRol> findAll();
    CatRol findById(Long id);
    CatRol save(CatRol catRolModel) throws Exception;
    CatRol create(Map<String, Object> params) throws Exception;
    CatRol update(CatRol catRolModel, Map<String, Object> params) throws Exception;
    CatRol build(Map<String, Object> params, CatRol catRolModel) throws IllegalArgumentException;
    CatRol updateInstance(CatRol catRolModel) throws Exception;
    void deleteById(Long id);
}
