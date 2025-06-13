/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMediosEstudio;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatMediosEstudioRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatMediosEstudioService;
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
public class CatMediosEstudioServiceJPA implements ICatMediosEstudioService {
    @Autowired
    private CatMediosEstudioRepository repository;

    @Override
    public List<CatMediosEstudio> findAll() {
        return repository.findAll();
    }

    @Override
    public CatMediosEstudio findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medio de estudio no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public CatMediosEstudio save(CatMediosEstudio model) throws Exception {
        return repository.save(model);
    }

    @Override
    public CatMediosEstudio create(Map<String, Object> params) throws Exception {
        CatMediosEstudio model = new CatMediosEstudio();
        model = this.build(params, model);
        return this.save(model);
    }

    @Override
    public CatMediosEstudio update(CatMediosEstudio model, Map<String, Object> params) throws Exception {
        model = this.build(params, model);
        return this.save(model);
    }

    @Override
    public CatMediosEstudio build(Map<String, Object> params, CatMediosEstudio model) {
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
    public CatMediosEstudio updateInstance(CatMediosEstudio instance) throws Exception {
        CatMediosEstudio dbModel = this.findById(instance.getId());
        dbModel.setNombreMedios(instance.getNombreMedios());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        CatMediosEstudio model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
