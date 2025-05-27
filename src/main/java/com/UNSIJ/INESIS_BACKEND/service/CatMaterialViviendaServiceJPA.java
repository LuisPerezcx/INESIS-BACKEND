/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMaterialViviendaModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatMaterialViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatMaterialViviendaService;
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
public class CatMaterialViviendaServiceJPA implements ICatMaterialViviendaService {

    @Autowired
    private CatMaterialViviendaRepository repository;

    @Override
    public List<CatMaterialViviendaModel> findAll() {
        return repository.findAll();
    }

    @Override
    public CatMaterialViviendaModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Material de vivienda no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public CatMaterialViviendaModel save(CatMaterialViviendaModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatMaterialViviendaModel create(Map<String, Object> params) throws Exception {
        CatMaterialViviendaModel model = new CatMaterialViviendaModel();
        model = this.build(params, model);
        return this.save(model);
    }

    @Override
    public CatMaterialViviendaModel update(CatMaterialViviendaModel model, Map<String, Object> params) throws Exception {
        model = this.build(params, model);
        return this.save(model);
    }

    @Override
    public CatMaterialViviendaModel build(Map<String, Object> params, CatMaterialViviendaModel model) {
        try {
            String nombreMaterial = JsonUtils.obtString(params, "nombreMaterial");
            if (nombreMaterial == null || nombreMaterial.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo 'nombreMaterial' es obligatorio");
            }
            model.setNombreMaterial(nombreMaterial);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al procesar los datos del material de vivienda");
        }
        return model;
    }

    @Override
    public CatMaterialViviendaModel updateInstance(CatMaterialViviendaModel instance) throws Exception {
        CatMaterialViviendaModel dbModel = this.findById(instance.getId());
        dbModel.setNombreMaterial(instance.getNombreMaterial());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatMaterialViviendaModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
