package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.CatMediosTransporte;

import java.util.List;
import java.util.Map;

public interface ICatMediosTransporteService {
    List<CatMediosTransporte> findAll();

    CatMediosTransporte findById(Long id);

    CatMediosTransporte save(CatMediosTransporte catMediosTransporte) throws Exception;

    CatMediosTransporte create(Map<String, Object> params) throws Exception;

    CatMediosTransporte update(CatMediosTransporte catMediosTransporte, Map<String, Object> params) throws Exception;

    CatMediosTransporte build(Map<String, Object> params, CatMediosTransporte catMediosTransporte) throws IllegalArgumentException;

    CatMediosTransporte updateInstance(CatMediosTransporte catMediosTransporte) throws Exception;

    void deleteById(Long id);
}
