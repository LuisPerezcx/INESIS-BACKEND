package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatEstadoCivil;

public interface ICatEstadoCivilService {
    List<CatEstadoCivil> findAll();
    CatEstadoCivil findById(Long id);
    CatEstadoCivil save(CatEstadoCivil catEstadoCivil) throws Exception;
    CatEstadoCivil create(Map<String, Object> params) throws Exception;
    CatEstadoCivil update(CatEstadoCivil catEstadoCivil, Map<String, Object> params) throws Exception;
    CatEstadoCivil build(Map<String, Object> params, CatEstadoCivil catEstadoCivil) throws IllegalArgumentException;
    CatEstadoCivil updateInstance(CatEstadoCivil catEstadoCivil) throws Exception;
    void deleteById(Long id);
}
