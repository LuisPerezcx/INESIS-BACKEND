/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatSituacionVivienda;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatSituacionViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatSituacionViviendaService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 24mda
 */
@Service
public class CatSituacionViviendaServiceJPA implements ICatSituacionViviendaService {
    @Autowired
    private CatSituacionViviendaRepository repository;

    @Override
    public List<CatSituacionVivienda> findAll() {
        return repository.findAll();
    }

    @Override
    public CatSituacionVivienda findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Situación de vivienda no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public CatSituacionVivienda save(CatSituacionVivienda model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatSituacionVivienda create(Map<String, Object> params) throws Exception {
        CatSituacionVivienda model = new CatSituacionVivienda();
        return this.save(this.build(params, model));
    }

    @Override
    public CatSituacionVivienda update(CatSituacionVivienda model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public CatSituacionVivienda build(Map<String, Object> params, CatSituacionVivienda model) {
        String nombre = JsonUtils.obtString(params, "nombreSituacion");
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'nombreSituacion' es obligatorio.");
        }
        model.setNombreSituacion(nombre);
        return model;
    }

    @Override
    public CatSituacionVivienda updateInstance(CatSituacionVivienda instance) throws Exception {
        CatSituacionVivienda dbModel = this.findById(instance.getId());
        dbModel.setNombreSituacion(instance.getNombreSituacion());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatSituacionVivienda model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
