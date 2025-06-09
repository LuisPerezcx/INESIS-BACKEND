package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.ReciboLuzModel;

public interface IReciboLuz {
    List<ReciboLuzModel> findAll();
    ReciboLuzModel findById(Long id);
    ReciboLuzModel save(ReciboLuzModel ReciboLuzModel) throws Exception;
    ReciboLuzModel create(Map<String, Object> params) throws Exception;
    ReciboLuzModel update(ReciboLuzModel ReciboLuzModel, Map<String, Object> params) throws Exception;
    ReciboLuzModel build(Map<String, Object> params, ReciboLuzModel ReciboLuzModel) throws IllegalArgumentException;
    void deleteById(Long id);
}
