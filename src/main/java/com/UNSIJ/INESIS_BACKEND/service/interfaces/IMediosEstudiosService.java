/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MediosEstudio;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
public interface IMediosEstudiosService {
    List<MediosEstudio> findAll();

    MediosEstudio findById(Long id);

    MediosEstudio save(MediosEstudio model) throws Exception;

    MediosEstudio create(Long idCatMedios, MiFamilia miFamilia) throws Exception;


    MediosEstudio build(Long idCatMedios, MediosEstudio model, MiFamilia miFamilia);

    MediosEstudio updateInstance(MediosEstudio instance) throws Exception;

    void deleteById(Long id);
}
