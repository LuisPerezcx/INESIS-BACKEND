package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.CatTipoTransporte;

public interface ICatTipoTransporteService {
    List<CatTipoTransporte> findAll();
    CatTipoTransporte findById(Long id);
    CatTipoTransporte save(CatTipoTransporte catTipoTransporte) throws Exception;
    CatTipoTransporte create(Map<String, Object> params) throws Exception;
    CatTipoTransporte update(CatTipoTransporte catTipoTransporte, Map<String, Object> params) throws Exception;
    CatTipoTransporte build(Map<String, Object> params, CatTipoTransporte catTipoTransporte) throws IllegalArgumentException;
    CatTipoTransporte updateInstance(CatTipoTransporte catTipoTransporte) throws Exception;
    void deleteById(Long id);
}
