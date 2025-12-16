/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.MiFamilia;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.PersonasDependientes;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.MiFamiliaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.PersonasDependientesRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IPersonasDependientesService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Alumnos
 */
@Service
public class PersonasDependientesServiceJPA implements IPersonasDependientesService {

    @Autowired
    private PersonasDependientesRepository repository;

    @Autowired
    private CatParentescoServiceJPA catParentescoService;

    @Autowired
    ArchivoServiceJPA archivoService;

    @Override
    public List<PersonasDependientes> findAll() {
        return repository.findAll();
    }

    @Override
    public PersonasDependientes findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("HermanosDependientes no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public PersonasDependientes save(PersonasDependientes model) throws Exception {
        return repository.save(model);
    }

    @Override
    public PersonasDependientes create(Map<String, Object> params, MiFamilia miFamilia) throws Exception {
        PersonasDependientes model = new PersonasDependientes();
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
    @Transactional
    public PersonasDependientes update(PersonasDependientes model, Map<String, Object> params) throws Exception {
        String nuevoNombre = JsonUtils.obtString(params, "nombrePersona");
        Integer nuevaEdad = JsonUtils.obtInteger(params, "edad");
        Long nuevoParentescoId = JsonUtils.obtLong(params, "parentesco");
        String nuevoNombreArchivo = JsonUtils.obtString(params, "archivo.name");
        String nuevoArchivoBase64 = JsonUtils.obtString(params, "archivo.contenido");

        // Solo actualiza si hay cambios reales
        if (nuevoNombre != null && !nuevoNombre.equals(model.getNombrePersona())) {
            model.setNombrePersona(nuevoNombre);
        }

        if (nuevaEdad != null && !nuevaEdad.equals(model.getEdad())) {
            model.setEdad(nuevaEdad);
        }

        if (nuevoParentescoId != null &&
                (model.getParentesco() == null || !nuevoParentescoId.equals(model.getParentesco().getId()))) {
            model.setParentesco(catParentescoService.findById(nuevoParentescoId));
        }

        if (nuevoNombreArchivo != null) {
            model.setNombreArchivo(nuevoNombreArchivo);
        }

        // Manejo de archivo
        if (nuevoArchivoBase64 != null && !nuevoArchivoBase64.isEmpty()) {
            String rutaAnterior = model.getRutaArchivo();

            String nombreCarpeta1 = (model.getMiFamilia() != null && model.getMiFamilia().getAlumno() != null) ?
                    "alumno_" + model.getMiFamilia().getAlumno().getId() :
                    "alumno_" + (model.getMiFamilia() != null ? model.getMiFamilia().getId() : "desconocido");

            System.out.println("Carpeta para guardar archivo: " + nombreCarpeta1);
            // Guardar archivo nuevo
            String nombreCarpeta = model.getMiFamilia() != null && model.getMiFamilia().getAlumno() != null ?
                    model.getMiFamilia().getAlumno().getNombreCompleto().replace(" ", "_") :
                    "alumno_" + (model.getMiFamilia() != null ? model.getMiFamilia().getId() : "desconocido");

            String rutaNueva = archivoService.guardarArchivoBase64(
                    nuevoArchivoBase64,
                    nuevoNombreArchivo,
                    "personas-dependientes",
                    nombreCarpeta,
                    false
            );

            // Solo elimina si el archivo es diferente
            if (rutaAnterior != null && !rutaAnterior.equals(rutaNueva)) {
                archivoService.eliminarArchivo(rutaAnterior);
            }

            model.setRutaArchivo(rutaNueva);
        }

        return repository.save(model);
    }

    @Override
    @Transactional
    public PersonasDependientes build(Map<String, Object> params, PersonasDependientes model, MiFamilia miFamilia) {
        model.setNombrePersona(JsonUtils.obtString(params, "nombrePersona"));
        model.setEdad(JsonUtils.obtInteger(params, "edad"));
        model.setParentesco(catParentescoService.findById(JsonUtils.obtLong(params, "parentesco")));
        model.setNombreArchivo(JsonUtils.obtString(params, "archivo.name"));

        String archivoBase64 = JsonUtils.obtString(params, "archivo.contenido");
        if (archivoBase64 != null && !archivoBase64.isEmpty()) {
            String nuevoNombre = JsonUtils.obtString(params, "archivo.name");
            String nuevoArchivoBase64 = JsonUtils.obtString(params, "archivo.contenido");

            String nombreArchivoAnterior = model.getRutaArchivo();

            if (nuevoArchivoBase64 != null && !nuevoArchivoBase64.isEmpty()) {
                // Si el nombre del archivo es igual al anterior, asumimos que no cambió
                if (nombreArchivoAnterior != null && nombreArchivoAnterior.contains(nuevoNombre)) {
                    System.out.println("Archivo ya existente, no se reemplaza.");
                } else {
                    try {
                        String nombreCarpeta = miFamilia.getAlumno() != null ?
                                "alumno_" + miFamilia.getAlumno().getId() :
                                "alumno_Fam_" + miFamilia.getId();

                        // Guardar archivo nuevo
                        String rutaRelativa = archivoService.guardarArchivoBase64(
                                nuevoArchivoBase64,
                                nuevoNombre,
                                "personas-dependientes",
                                nombreCarpeta,
                                false
                        );

                        if (nombreArchivoAnterior != null) {
                            archivoService.eliminarArchivo(nombreArchivoAnterior);
                        }

                        model.setRutaArchivo(rutaRelativa);
                    } catch (IOException e) {
                        throw new IllegalArgumentException("Error al guardar archivo: " + e.getMessage());
                    }
                }
            }
        }
        model.setMiFamilia(miFamilia);
        return model;
    }

    @Override
    public void deleteById(Long id) {
        PersonasDependientes model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }
}
