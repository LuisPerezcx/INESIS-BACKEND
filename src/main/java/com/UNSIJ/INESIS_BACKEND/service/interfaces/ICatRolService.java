package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatRolModel;

public interface ICatRolService {
    List<CatRolModel> findAll();
    CatRolModel findById(Long id);
    CatRolModel save(CatRolModel catRolModel) throws Exception;
    CatRolModel create(Map<String, Object> params) throws Exception;
    CatRolModel update(CatRolModel catRolModel, Map<String, Object> params) throws Exception;
    CatRolModel build(Map<String, Object> params, CatRolModel catRolModel) throws IllegalArgumentException;
    CatRolModel updateInstance(CatRolModel catRolModel) throws Exception;
    void deleteById(Long id);
}
