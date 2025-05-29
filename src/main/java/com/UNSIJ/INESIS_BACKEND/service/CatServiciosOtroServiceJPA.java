/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatServiciosOtro;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatServiciosOtroRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatServiciosOtroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 *
 * @author Alumnos
 */
public class CatServiciosOtroServiceJPA implements ICatServiciosOtroService{
    @Autowired
    private CatServiciosOtroRepository repository;

    @Override
    public List<CatServiciosOtro> findAll() {
        return repository.findAll();
    }

    @Override
    public CatServiciosOtro findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servicio Otro no encontrado con ID: " + id));
    }
}
