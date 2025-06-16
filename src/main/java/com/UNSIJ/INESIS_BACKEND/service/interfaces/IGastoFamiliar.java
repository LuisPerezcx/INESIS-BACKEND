package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.GastoFamiliarModel;

public interface IGastoFamiliar {
    List<GastoFamiliarModel> findAll();

    GastoFamiliarModel findById(Long id);

    GastoFamiliarModel save(GastoFamiliarModel GastoFamiliarModel) throws Exception;

    GastoFamiliarModel create(Map<String, Object> params) throws Exception;

    GastoFamiliarModel update(GastoFamiliarModel GastoFamiliarModel, Map<String, Object> params) throws Exception;

    GastoFamiliarModel build(Map<String, Object> params, GastoFamiliarModel GastoFamiliarModel)
            throws IllegalArgumentException;

    void deleteById(Long id);
}
