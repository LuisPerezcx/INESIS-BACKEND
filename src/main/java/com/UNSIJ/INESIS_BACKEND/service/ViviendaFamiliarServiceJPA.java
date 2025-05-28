/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMaterialViviendaModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatSituacionViviendaModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatTipoViviendaModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliarModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatMaterialViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatSituacionViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatTipoViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.ViviendaFamiliarRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IViviendaFamiliarService;
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
public class ViviendaFamiliarServiceJPA implements IViviendaFamiliarService{
 @Autowired
    private ViviendaFamiliarRepository repository;

    @Autowired
    private CatSituacionViviendaRepository situacionViviendaRepository;

    @Autowired
    private CatTipoViviendaRepository tipoViviendaRepository;

    @Autowired
    private CatMaterialViviendaRepository materialViviendaRepository;

    @Override
    public List<ViviendaFamiliarModel> findAll() {
        return repository.findAll();
    }

    @Override
    public ViviendaFamiliarModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vivienda Familiar no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public ViviendaFamiliarModel save(ViviendaFamiliarModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public ViviendaFamiliarModel create(Map<String, Object> params) throws Exception {
        ViviendaFamiliarModel model = new ViviendaFamiliarModel();
        return this.save(this.build(params, model));
    }

    @Override
    public ViviendaFamiliarModel update(ViviendaFamiliarModel model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public ViviendaFamiliarModel build(Map<String, Object> params, ViviendaFamiliarModel model) {
        Integer numPersonas = JsonUtils.obtInteger(params, "num_personas_habitan");
        String serviciosOtro = (String) params.get("servicios_otro");

        if (numPersonas != null) {
            model.setNumPersonasHabitan(numPersonas);
        }
        model.setServiciosOtro(serviciosOtro);

        // Para las relaciones ManyToOne
        Long idSituacion = JsonUtils.obtLong(params, "id_cat_situacion_vivienda");
        if (idSituacion == null) {
            throw new IllegalArgumentException("El campo 'id_cat_situacion_vivienda' es obligatorio.");
        }
        CatSituacionViviendaModel situacion = situacionViviendaRepository.findById(idSituacion)
                .orElseThrow(() -> new IllegalArgumentException("Situación vivienda no encontrada con ID: " + idSituacion));
        model.setSituacionVivienda(situacion);

        Long idTipo = JsonUtils.obtLong(params, "id_cat_tipo_vivienda");
        if (idTipo == null) {
            throw new IllegalArgumentException("El campo 'id_cat_tipo_vivienda' es obligatorio.");
        }
        CatTipoViviendaModel tipo = tipoViviendaRepository.findById(idTipo)
                .orElseThrow(() -> new IllegalArgumentException("Tipo vivienda no encontrado con ID: " + idTipo));
        model.setTipoVivienda(tipo);

        Long idMaterial = JsonUtils.obtLong(params, "id_cat_material_vivienda");
        if (idMaterial == null) {
            throw new IllegalArgumentException("El campo 'id_cat_material_vivienda' es obligatorio.");
        }
        CatMaterialViviendaModel material = materialViviendaRepository.findById(idMaterial)
                .orElseThrow(() -> new IllegalArgumentException("Material vivienda no encontrado con ID: " + idMaterial));
        model.setMaterialVivienda(material);

        return model;
    }

    @Override
    public ViviendaFamiliarModel updateInstance(ViviendaFamiliarModel instance) throws Exception {
        ViviendaFamiliarModel dbModel = this.findById(instance.getId());
        dbModel.setNumPersonasHabitan(instance.getNumPersonasHabitan());
        dbModel.setServiciosOtro(instance.getServiciosOtro());
        dbModel.setSituacionVivienda(instance.getSituacionVivienda());
        dbModel.setTipoVivienda(instance.getTipoVivienda());
        dbModel.setMaterialVivienda(instance.getMaterialVivienda());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        ViviendaFamiliarModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
