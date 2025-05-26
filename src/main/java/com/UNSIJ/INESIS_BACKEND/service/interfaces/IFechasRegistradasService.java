package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.FechasRegistradasModel;

import java.util.List;
import java.util.Map;

public interface IFechasRegistradasService {
    List<FechasRegistradasModel> findAll();
    FechasRegistradasModel findById(Long id);
    FechasRegistradasModel save(FechasRegistradasModel fechasRegistradas) throws Exception;
    FechasRegistradasModel create(Map<String, Object> params) throws Exception;
    FechasRegistradasModel update(FechasRegistradasModel fechasRegistradas, Map<String, Object> params) throws Exception;
    FechasRegistradasModel build(Map<String, Object> params, FechasRegistradasModel fechasRegistradas) throws IllegalArgumentException;
    FechasRegistradasModel updateInstance(FechasRegistradasModel fechasRegistradas) throws Exception;
    void deleteById(Long id);
}
