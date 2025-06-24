/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.BienesHogar;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
public interface IBienesHogarService {

    List<BienesHogar> findAll();

    BienesHogar findById(Long id);

    BienesHogar save(BienesHogar model) throws Exception;

    BienesHogar create(Long idBienes, MiFamilia miFamilia) throws Exception;

    BienesHogar build(Long idBienes, BienesHogar model, MiFamilia miFamilia);

    BienesHogar updateInstance(BienesHogar instance) throws Exception;

    void deleteById(Long id);
}
