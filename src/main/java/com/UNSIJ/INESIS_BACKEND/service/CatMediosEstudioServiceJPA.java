/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMediosEstudioModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatMediosEstudioRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatMediosEstudioService;
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
public class CatMediosEstudioServiceJPA implements ICatMediosEstudioService{
    @Autowired
    private CatMediosEstudioRepository repository;

    @Override
    public List<CatMediosEstudioModel> findAll() {
        return repository.findAll();
    }

    @Override
    public CatMediosEstudioModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medio de estudio no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public CatMediosEstudioModel save(CatMediosEstudioModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatMediosEstudioModel create(Map<String, Object> params) throws Exception {
        CatMediosEstudioModel model = new CatMediosEstudioModel();
        model = this.build(params, model);
        return this.save(model);
    }

    @Override
    public CatMediosEstudioModel update(CatMediosEstudioModel model, Map<String, Object> params) throws Exception {
        model = this.build(params, model);
        return this.save(model);
    }

    @Override
    public CatMediosEstudioModel build(Map<String, Object> params, CatMediosEstudioModel model) {
        try {
            String nombreMedio = JsonUtils.obtString(params, "nombreMedio");
            if (nombreMedio == null || nombreMedio.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo 'nombreMedio' es obligatorio");
            }
            model.setNombreMedios(nombreMedio);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al procesar los datos del medio de estudio");
        }
        return model;
    }

    @Override
    public CatMediosEstudioModel updateInstance(CatMediosEstudioModel instance) throws Exception {
        CatMediosEstudioModel dbModel = this.findById(instance.getId());
        dbModel.setNombreMedios(instance.getNombreMedios());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatMediosEstudioModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
