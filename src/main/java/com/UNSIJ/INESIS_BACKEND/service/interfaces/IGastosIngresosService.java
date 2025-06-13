package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.GastosIngresos;

import java.util.List;
import java.util.Map;

public interface IGastosIngresosService {
    List<GastosIngresos> findAll();

    GastosIngresos findById(Long id);

    GastosIngresos save(GastosIngresos gastosIngresos) throws Exception;

    GastosIngresos create(Map<String, Object> params) throws Exception;

    GastosIngresos update(GastosIngresos gastosIngresos, Map<String, Object> params) throws Exception;

    GastosIngresos build(Map<String, Object> params, GastosIngresos gastosIngresos) throws IllegalArgumentException;

    GastosIngresos updateInstance(GastosIngresos gastosIngresos) throws Exception;

    void deleteById(Long id);
}
