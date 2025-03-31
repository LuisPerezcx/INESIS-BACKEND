package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatSemestreModel;

public interface ICatSemestreService {
    List<CatSemestreModel> findAll();
    CatSemestreModel findById(Long id);
    CatSemestreModel save(CatSemestreModel catSemestreModel) throws Exception;
    CatSemestreModel create(Map<String, Object> params) throws Exception;
    CatSemestreModel update(CatSemestreModel catSemestreModel, Map<String, Object> params) throws Exception;
    CatSemestreModel build(Map<String, Object> params, CatSemestreModel catSemestreModel) throws IllegalArgumentException;
    CatSemestreModel updateInstance(CatSemestreModel catSemestreModel) throws Exception;
    void deleteById(Long id);
    
}
