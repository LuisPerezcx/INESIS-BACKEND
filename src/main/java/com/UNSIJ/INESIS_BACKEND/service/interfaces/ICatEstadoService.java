package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatEstado;

public interface ICatEstadoService {
    List<CatEstado> findAll();
    CatEstado findById(Long id);
    CatEstado save(CatEstado catEstado) throws Exception;
    CatEstado create(Map<String, Object> params) throws Exception;
    CatEstado update(CatEstado catEstado, Map<String, Object> params) throws Exception;
    CatEstado build(Map<String, Object> params, CatEstado catEstado) throws IllegalArgumentException;
    CatEstado updateInstance(CatEstado catEstado) throws Exception;
    void deleteById(Long id);
}
