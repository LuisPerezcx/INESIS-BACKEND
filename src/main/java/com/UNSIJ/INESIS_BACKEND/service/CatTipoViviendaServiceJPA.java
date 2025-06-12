/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatTipoVivienda;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatTipoViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatTipoViviendaService;
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
public class CatTipoViviendaServiceJPA implements ICatTipoViviendaService {
    @Autowired
    private CatTipoViviendaRepository repository;

    @Override
    public List<CatTipoVivienda> findAll() {
        return repository.findAll();
    }

    @Override
    public CatTipoVivienda findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de vivienda no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public CatTipoVivienda save(CatTipoVivienda model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatTipoVivienda create(Map<String, Object> params) throws Exception {
        CatTipoVivienda model = new CatTipoVivienda();
        return this.save(this.build(params, model));
    }

    @Override
    public CatTipoVivienda update(CatTipoVivienda model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public CatTipoVivienda build(Map<String, Object> params, CatTipoVivienda model) {
        String nombre = JsonUtils.obtString(params, "nombreTipo");
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'nombreTipo' es obligatorio.");
        }
        model.setNombreTipo(nombre);
        return model;
    }

    @Override
    public CatTipoVivienda updateInstance(CatTipoVivienda instance) throws Exception {
        CatTipoVivienda dbModel = this.findById(instance.getId());
        dbModel.setNombreTipo(instance.getNombreTipo());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatTipoVivienda model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
