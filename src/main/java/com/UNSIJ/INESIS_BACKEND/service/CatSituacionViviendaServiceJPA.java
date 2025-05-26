/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatSituacionViviendaModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatSituacionViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatSituacionViviendaService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
/**
 *
 * @author 24mda
 */
@Service
public class CatSituacionViviendaServiceJPA implements ICatSituacionViviendaService{
    @Autowired
    private CatSituacionViviendaRepository repository;

    @Override
    public List<CatSituacionViviendaModel> findAll() {
        return repository.findAll();
    }

    @Override
    public CatSituacionViviendaModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Situación de vivienda no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public CatSituacionViviendaModel save(CatSituacionViviendaModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatSituacionViviendaModel create(Map<String, Object> params) throws Exception {
        CatSituacionViviendaModel model = new CatSituacionViviendaModel();
        return this.save(this.build(params, model));
    }

    @Override
    public CatSituacionViviendaModel update(CatSituacionViviendaModel model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public CatSituacionViviendaModel build(Map<String, Object> params, CatSituacionViviendaModel model) {
        String nombre = JsonUtils.obtString(params, "nombreSituacion");
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'nombreSituacion' es obligatorio.");
        }
        model.setNombreSituacion(nombre);
        return model;
    }

    @Override
    public CatSituacionViviendaModel updateInstance(CatSituacionViviendaModel instance) throws Exception {
        CatSituacionViviendaModel dbModel = this.findById(instance.getId());
        dbModel.setNombreSituacion(instance.getNombreSituacion());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatSituacionViviendaModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
