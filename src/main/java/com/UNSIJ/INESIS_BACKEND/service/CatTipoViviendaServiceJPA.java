/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatTipoViviendaModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatTipoViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatTipoViviendaService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
/**
 *
 * @author 24mda
 */
@Service
public class CatTipoViviendaServiceJPA implements ICatTipoViviendaService{
@Autowired
    private CatTipoViviendaRepository repository;

    @Override
    public List<CatTipoViviendaModel> findAll() {
        return repository.findAll();
    }

    @Override
    public CatTipoViviendaModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de vivienda no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public CatTipoViviendaModel save(CatTipoViviendaModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatTipoViviendaModel create(Map<String, Object> params) throws Exception {
        CatTipoViviendaModel model = new CatTipoViviendaModel();
        return this.save(this.build(params, model));
    }

    @Override
    public CatTipoViviendaModel update(CatTipoViviendaModel model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public CatTipoViviendaModel build(Map<String, Object> params, CatTipoViviendaModel model) {
        String nombre = JsonUtils.obtString(params, "nombreTipo");
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'nombreTipo' es obligatorio.");
        }
        model.setNombreTipo(nombre);
        return model;
    }

    @Override
    public CatTipoViviendaModel updateInstance(CatTipoViviendaModel instance) throws Exception {
        CatTipoViviendaModel dbModel = this.findById(instance.getId());
        dbModel.setNombreTipo(instance.getNombreTipo());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatTipoViviendaModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
