/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatServiciosVivienda;

import java.util.List;

/**
 * @author Alumnos
 */
public interface ICatServiciosOtroService {
    List<CatServiciosVivienda> findAll();

    CatServiciosVivienda findById(Long id);
}
