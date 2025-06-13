package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.CatSemestre;

import java.util.List;
import java.util.Map;

public interface ICatSemestreService {
    List<CatSemestre> findAll();

    CatSemestre findById(Long id);

    CatSemestre save(CatSemestre catSemestre) throws Exception;

    CatSemestre create(Map<String, Object> params) throws Exception;

    CatSemestre update(CatSemestre catSemestre, Map<String, Object> params) throws Exception;

    CatSemestre build(Map<String, Object> params, CatSemestre catSemestre) throws IllegalArgumentException;

    CatSemestre updateInstance(CatSemestre catSemestre) throws Exception;

    void deleteById(Long id);

}
