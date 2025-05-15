/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MediosEstudioModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamiliaModel;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliarModel;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.MediosEstudioRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.MiFamiliaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.ViviendaFamiliarRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IMiFamiliaService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 *
 * @author 24mda
 */
@Service
public class MiFamiliaServiceJPA implements IMiFamiliaService{
    @Autowired
    private MiFamiliaRepository repository;

    @Autowired
    private ViviendaFamiliarRepository viviendaRepo;

    @Autowired
    private MediosEstudioRepository mediosRepo;

    @Override
    public List<MiFamiliaModel> findAll() {
        return repository.findAll();
    }

    @Override
    public MiFamiliaModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MiFamilia no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public MiFamiliaModel save(MiFamiliaModel model) throws Exception {
        return repository.save(model);
    }

    @Override
    public MiFamiliaModel create(Map<String, Object> params) throws Exception {
        MiFamiliaModel model = new MiFamiliaModel();
        return this.save(this.build(params, model));
    }

    @Override
    public MiFamiliaModel update(MiFamiliaModel model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public MiFamiliaModel build(Map<String, Object> params, MiFamiliaModel model) {
        model.setNombreCompleto(JsonUtils.obtString(params, "nombre_completo"));
        model.setIdDomicilio(JsonUtils.obtInteger(params, "id_domicilio"));
        model.setTelefono(JsonUtils.obtInteger(params, "telefono"));
        model.setEscolaridadPadre(JsonUtils.obtString(params, "escolaridadPadre"));
        model.setEscolaridadMadre(JsonUtils.obtString(params, "escolaridadMadre"));
        model.setNumHermanos(JsonUtils.obtInteger(params, "num_hermanos"));
        model.setNumHermanosEstudiando(JsonUtils.obtInteger(params, "num_hermanos_estudiando"));
        model.setNumHermanosNoEstudiando(JsonUtils.obtInteger(params, "num_hermanos_no_estudiando"));
        model.setNumHermanosLicenciatura(JsonUtils.obtInteger(params, "num_hermanos_licenciatura"));

        Long idVivienda = JsonUtils.obtLong(params, "id_vivienda_familiar");
        Long idMedios = JsonUtils.obtLong(params, "id_medios_estudio");

        if (idVivienda != null) {
            ViviendaFamiliarModel vivienda = viviendaRepo.findById(idVivienda)
                .orElseThrow(() -> new IllegalArgumentException("Vivienda no encontrada con ID: " + idVivienda));
            model.setViviendaFamiliar(vivienda);
        }

        if (idMedios != null) {
            MediosEstudioModel medios = mediosRepo.findById(idMedios)
                .orElseThrow(() -> new IllegalArgumentException("Medios de estudio no encontrado con ID: " + idMedios));
            model.setMediosEstudio(medios);
        }

        return model;
    }

    @Override
    public void deleteById(Long id) {
        MiFamiliaModel model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }

}
