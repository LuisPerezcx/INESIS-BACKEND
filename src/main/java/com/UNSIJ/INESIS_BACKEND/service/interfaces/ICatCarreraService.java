package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatCarreraModel;

public interface ICatCarreraService {
    List<CatCarreraModel> findAll();
    CatCarreraModel findById(Long id);
    CatCarreraModel save(CatCarreraModel catCarreraModel) throws Exception;
    CatCarreraModel create(Map<String, Object> params) throws Exception;
    CatCarreraModel update(CatCarreraModel catCarreraModel, Map<String, Object> params) throws Exception;
    CatCarreraModel build(Map<String, Object> params, CatCarreraModel catCarreraModel) throws IllegalArgumentException;
    CatCarreraModel updateInstance(CatCarreraModel catCarreraModel) throws Exception;
    void deleteById(Long id);
    
}
