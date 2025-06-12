/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatMaterialVivienda;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatSituacionVivienda;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatTipoVivienda;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliar;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatMaterialViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatSituacionViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatTipoViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.ViviendaFamiliarRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IViviendaFamiliarService;
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
public class ViviendaFamiliarServiceJPA implements IViviendaFamiliarService {
    @Autowired
    private ViviendaFamiliarRepository repository;

    @Autowired
    private CatSituacionViviendaRepository situacionViviendaRepository;

    @Autowired
    private CatTipoViviendaRepository tipoViviendaRepository;

    @Autowired
    private CatMaterialViviendaRepository materialViviendaRepository;

    @Override
    public List<ViviendaFamiliar> findAll() {
        return repository.findAll();
    }

    @Override
    public ViviendaFamiliar findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vivienda Familiar no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public ViviendaFamiliar save(ViviendaFamiliar model) throws Exception {
        return repository.save(model);
    }

    @Override
    public ViviendaFamiliar create(Map<String, Object> params) throws Exception {
        ViviendaFamiliar model = new ViviendaFamiliar();
        return this.save(this.build(params, model));
    }

    @Override
    public ViviendaFamiliar update(ViviendaFamiliar model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public ViviendaFamiliar build(Map<String, Object> params, ViviendaFamiliar model) {
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
        CatSituacionVivienda situacion = situacionViviendaRepository.findById(idSituacion)
                .orElseThrow(() -> new IllegalArgumentException("Situación vivienda no encontrada con ID: " + idSituacion));
        model.setSituacionVivienda(situacion);

        Long idTipo = JsonUtils.obtLong(params, "id_cat_tipo_vivienda");
        if (idTipo == null) {
            throw new IllegalArgumentException("El campo 'id_cat_tipo_vivienda' es obligatorio.");
        }
        CatTipoVivienda tipo = tipoViviendaRepository.findById(idTipo)
                .orElseThrow(() -> new IllegalArgumentException("Tipo vivienda no encontrado con ID: " + idTipo));
        model.setTipoVivienda(tipo);

        Long idMaterial = JsonUtils.obtLong(params, "id_cat_material_vivienda");
        if (idMaterial == null) {
            throw new IllegalArgumentException("El campo 'id_cat_material_vivienda' es obligatorio.");
        }
        CatMaterialVivienda material = materialViviendaRepository.findById(idMaterial)
                .orElseThrow(() -> new IllegalArgumentException("Material vivienda no encontrado con ID: " + idMaterial));
        model.setMaterialVivienda(material);

        return model;
    }

    @Override
    public ViviendaFamiliar updateInstance(ViviendaFamiliar instance) throws Exception {
        ViviendaFamiliar dbModel = this.findById(instance.getId());
        dbModel.setNumPersonasHabitan(instance.getNumPersonasHabitan());
        dbModel.setServiciosOtro(instance.getServiciosOtro());
        dbModel.setSituacionVivienda(instance.getSituacionVivienda());
        dbModel.setTipoVivienda(instance.getTipoVivienda());
        dbModel.setMaterialVivienda(instance.getMaterialVivienda());
        return this.save(dbModel);
    }

    @Override
    public void deleteById(Long id) {
        ViviendaFamiliar model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
