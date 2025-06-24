/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.*;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatMaterialViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatSituacionViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatTipoViviendaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.ViviendaFamiliarRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IViviendaFamiliarService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private CatSituacionViviendaServiceJPA catSituacionViviendaService;
    @Autowired
    private CatTipoViviendaServiceJPA catTipoViviendaServiceJPA;
    @Autowired
    private CatMaterialViviendaServiceJPA catMaterialViviendaServiceJPA;
    @Autowired
    private ServiciosViviendaServiceJPA serviciosViviendaServiceJPA;
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
    public ViviendaFamiliar create(Map<String, Object> params, MiFamilia miFamilia) throws Exception {
        ViviendaFamiliar model = new ViviendaFamiliar();
        try {
            this.build(params, model, miFamilia);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el tramite");
        }
        return this.save(model);
    }

    @Override
    public ViviendaFamiliar update(ViviendaFamiliar model, Map<String, Object> params) throws Exception {
        try {
            MiFamilia miFamilia = model.getMiFamilia();
            this.build(params, model, miFamilia);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(model);
    }

    @Override
    @Transactional
    public ViviendaFamiliar build(Map<String, Object> params, ViviendaFamiliar model, MiFamilia miFamilia) {
        try {
            Integer numPersonas = JsonUtils.obtInteger(params, "num_personas_habitan");
            String serviciosOtro = JsonUtils.obtString(params, "servicios_otro");
            String region = JsonUtils.obtString(params, "domicilio.region");
            String distrito = JsonUtils.obtString(params, "domicilio.distrito");
            Long situacionViviendaId = JsonUtils.obtLong(params, "id_cat_situacion_vivienda");
            Long tipoViviendaId = JsonUtils.obtLong(params, "id_cat_tipo_vivienda");
            Long materialViviendaId = JsonUtils.obtLong(params, "id_cat_material_vivienda");

            if (numPersonas == null || numPersonas < 0)
                throw new IllegalArgumentException("El campo 'num_personas_habitan' " +
                        "es obligatorio y debe ser un número positivo");
            if (region == null) throw new IllegalArgumentException("El campo 'domicilio.region' es obligatorio");
            if (distrito == null) throw new IllegalArgumentException("El campo 'domicilio.distrito' es obligatorio");
            if (situacionViviendaId == null)
                throw new IllegalArgumentException("El campo 'id_cat_situacion_vivienda' es obligatorio");
            if (tipoViviendaId == null)
                throw new IllegalArgumentException("El campo 'id_cat_tipo_vivienda' es obligatorio");
            if (materialViviendaId == null)
                throw new IllegalArgumentException("El campo 'id_cat_material_vivienda' es obligatorio");

            model.setNumPersonasHabitan(numPersonas);
            if (serviciosOtro != null) model.setServiciosOtro(serviciosOtro);
            model.setRegion(region);
            model.setDistrito(distrito);
            model.setSituacionVivienda(catSituacionViviendaService.findById(situacionViviendaId));
            model.setTipoVivienda(catTipoViviendaServiceJPA.findById(tipoViviendaId));
            model.setMaterialVivienda(catMaterialViviendaServiceJPA.findById(materialViviendaId));

            //guardar el modelo antes de agregar los servicios
            model = this.save(model);

            List<Map<String, Object>> servicios = (List<Map<String, Object>>) params.get("serviciosVivienda");
            if (servicios != null) {
                model.getServiciosVivienda().clear();

                for (Map<String, Object> servicio : servicios) {
                    Long idCatServicio = JsonUtils.obtLong(servicio, "servicioViviendaId");
                    ServiciosVivienda servicioVivienda = serviciosViviendaServiceJPA.create(idCatServicio, model);
                    model.getServiciosVivienda().add(servicioVivienda); // Agregar a la misma lista
                }
            }

            model.setMiFamilia(miFamilia);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el alumno");
        }

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
