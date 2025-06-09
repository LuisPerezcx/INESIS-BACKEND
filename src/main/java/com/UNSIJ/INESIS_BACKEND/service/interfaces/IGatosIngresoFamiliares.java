package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.GastosIngresosFamiliares;

public interface IGatosIngresoFamiliares {
    List<GastosIngresosFamiliares> findAll();
    GastosIngresosFamiliares findById(Long id);
    GastosIngresosFamiliares save(GastosIngresosFamiliares GastosIngresosFamiliares) throws Exception;
    GastosIngresosFamiliares create(Map<String, Object> params) throws Exception;
    GastosIngresosFamiliares update(GastosIngresosFamiliares GastosIngresosFamiliares, Map<String, Object> params) throws Exception;
    GastosIngresosFamiliares build(Map<String, Object> params, GastosIngresosFamiliares GastosIngresosFamiliares) throws IllegalArgumentException;
    void deleteById(Long id);
}
