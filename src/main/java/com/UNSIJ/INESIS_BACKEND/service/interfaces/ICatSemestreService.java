package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatSemestre;

public interface ICatSemestreService {
    List<CatSemestre> findAll();
    CatSemestre findById(Long id);
    CatSemestre save(CatSemestre catSemestreModel) throws Exception;
    CatSemestre create(Map<String, Object> params) throws Exception;
    CatSemestre update(CatSemestre catSemestreModel, Map<String, Object> params) throws Exception;
    CatSemestre build(Map<String, Object> params, CatSemestre catSemestreModel) throws IllegalArgumentException;
    CatSemestre updateInstance(CatSemestre catSemestreModel) throws Exception;
    void deleteById(Long id);
    
}
