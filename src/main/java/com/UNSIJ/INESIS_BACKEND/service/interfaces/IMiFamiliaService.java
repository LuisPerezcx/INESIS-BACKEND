/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
public interface IMiFamiliaService {
    List<MiFamilia> findAll();

    MiFamilia findById(Long id);

    MiFamilia save(MiFamilia model) throws Exception;

    MiFamilia create(Map<String, Object> params) throws Exception;

    MiFamilia update(MiFamilia model, Map<String, Object> params) throws Exception;

    MiFamilia build(Map<String, Object> params, MiFamilia model);

    void deleteById(Long id);
}
