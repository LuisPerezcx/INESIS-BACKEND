package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import com.UNSIJ.INESIS_BACKEND.model.OcupacionModel;

import java.util.List;
import java.util.Map;

public interface IOcupacionService {
    List<OcupacionModel> findAll();
    OcupacionModel findById(Long id);
    OcupacionModel save(OcupacionModel ocupacionModel) throws Exception;
    OcupacionModel create(Map<String, Object> params) throws Exception;
    OcupacionModel update(OcupacionModel ocupacionModel, Map<String, Object> params) throws Exception;
    OcupacionModel build(Map<String, Object> params, OcupacionModel ocupacionModel) throws IllegalArgumentException;
    OcupacionModel updateInstance(OcupacionModel ocupacionModel) throws Exception;
    void deleteById(Long id);
}
