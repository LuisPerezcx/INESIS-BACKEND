/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatEscolaridad;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MediosEstudio;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.ViviendaFamiliar;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatEscolaridadRepository;
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
 * @author 24mda
 */
@Service
public class MiFamiliaServiceJPA implements IMiFamiliaService {
    @Autowired
    private MiFamiliaRepository repository;

    @Autowired
    private ViviendaFamiliarRepository viviendaRepo;

    @Autowired
    private CatEscolaridadRepository escolaridadRepo;

    @Autowired
    private MediosEstudioRepository mediosRepo;

    @Override
    public List<MiFamilia> findAll() {
        return repository.findAll();
    }

    @Override
    public MiFamilia findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MiFamilia no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public MiFamilia save(MiFamilia model) throws Exception {
        return repository.save(model);
    }

    @Override
    public MiFamilia create(Map<String, Object> params) throws Exception {
        MiFamilia model = new MiFamilia();
        try {
            this.build(params, model);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir misDatos (create)");
        }
        return this.save(model);
    }

    @Override
    public MiFamilia update(MiFamilia model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    public MiFamilia build(Map<String, Object> params, MiFamilia model) {
        try {
            model.setNombreCompleto(JsonUtils.obtString(params, "nombre_completo"));
            model.setIdDomicilio(JsonUtils.obtInteger(params, "id_domicilio"));
            model.setTelefono(JsonUtils.obtString(params, "telefono"));


            model.setNumHermanos(JsonUtils.obtInteger(params, "num_hermanos"));
            model.setNumHermanosEstudiando(JsonUtils.obtInteger(params, "num_hermanos_estudiando"));
            model.setNumHermanosNoEstudiando(JsonUtils.obtInteger(params, "num_hermanos_no_estudiando"));
            model.setNumHermanosLicenciatura(JsonUtils.obtInteger(params, "num_hermanos_licenciatura"));

            Long idVivienda = JsonUtils.obtLong(params, "id_cat_vivienda_familiar");
            Long idMedios = JsonUtils.obtLong(params, "id_medios_estudio");
            Long idEscolaridadPadre = JsonUtils.obtLong(params, "id_escolaridad_padre");
            Long idEscolaridadMadre = JsonUtils.obtLong(params, "id_escolaridad_madre");

            if (idVivienda != null) {
                ViviendaFamiliar vivienda = viviendaRepo.findById(idVivienda)
                        .orElseThrow(() -> new IllegalArgumentException("Vivienda no encontrada con ID: " + idVivienda));
                model.setViviendaFamiliar(vivienda);
            }

            if (idMedios != null) {
                MediosEstudio medios = mediosRepo.findById(idMedios)
                        .orElseThrow(() -> new IllegalArgumentException("Medios de estudio no encontrado con ID: " + idMedios));
                model.setMediosEstudio(medios);
            }

            if (idEscolaridadPadre != null) {
                CatEscolaridad escolaridadPadre = escolaridadRepo.findById(idEscolaridadPadre)
                        .orElseThrow(() -> new IllegalArgumentException("Escolaridad del padre no encontrada con ID: " + idEscolaridadPadre));
                model.setEscolaridadPadre(escolaridadPadre);
            }

            if (idEscolaridadMadre != null) {
                CatEscolaridad escolaridadMadre = escolaridadRepo.findById(idEscolaridadMadre)
                        .orElseThrow(() -> new IllegalArgumentException("Escolaridad de la madre no encontrada con ID: " + idEscolaridadMadre));
                model.setEscolaridadMadre(escolaridadMadre);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el alumno");
        }

        return model;
    }

    @Override
    public void deleteById(Long id) {
        MiFamilia model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }

}
