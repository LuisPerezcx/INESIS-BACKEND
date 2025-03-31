package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatSexoModel;

public interface ICatSexoService {
    List<CatSexoModel> findAll();
    CatSexoModel findById(Long id);
    CatSexoModel save(CatSexoModel catSexoModel) throws Exception;
    CatSexoModel create(Map<String, Object> params) throws Exception;
    CatSexoModel update(CatSexoModel catSexoModel, Map<String, Object> params) throws Exception;
    CatSexoModel build(Map<String, Object> params, CatSexoModel catSexoModel) throws IllegalArgumentException;
    CatSexoModel updateInstance(CatSexoModel catSexoModel) throws Exception;
    void deleteById(Long id);
}
