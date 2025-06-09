package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.IngresoFamiliarModel;

public interface IIngresoFamiliar {
        List<IngresoFamiliarModel> findAll();
    IngresoFamiliarModel findById(Long id);
    IngresoFamiliarModel save(IngresoFamiliarModel IngresoFamiliarModel) throws Exception;
    IngresoFamiliarModel create(Map<String, Object> params) throws Exception;
    IngresoFamiliarModel update(IngresoFamiliarModel IngresoFamiliarModel, Map<String, Object> params) throws Exception;
    IngresoFamiliarModel build(Map<String, Object> params, IngresoFamiliarModel IngresoFamiliarModel) throws IllegalArgumentException;
    IngresoFamiliarModel updateInstance(IngresoFamiliarModel IngresoFamiliarModel) throws Exception;
    void deleteById(Long id);
}
