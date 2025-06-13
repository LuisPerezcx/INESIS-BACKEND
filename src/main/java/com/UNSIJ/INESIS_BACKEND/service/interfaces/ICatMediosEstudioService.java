/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMediosEstudio;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
public interface ICatMediosEstudioService {

    List<CatMediosEstudio> findAll();

    CatMediosEstudio findById(Long id);

    CatMediosEstudio save(CatMediosEstudio model) throws Exception;

    CatMediosEstudio create(Map<String, Object> params) throws Exception;

    CatMediosEstudio update(CatMediosEstudio model, Map<String, Object> params) throws Exception;

    CatMediosEstudio build(Map<String, Object> params, CatMediosEstudio model);

    CatMediosEstudio updateInstance(CatMediosEstudio instance) throws Exception;

    void deleteById(Long id);
}
