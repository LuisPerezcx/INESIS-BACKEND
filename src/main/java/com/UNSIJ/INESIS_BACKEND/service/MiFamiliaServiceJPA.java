/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.model.Domicilio;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.*;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatEscolaridadRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.MediosEstudioRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.MiFamiliaRepository;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.ViviendaFamiliarRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IMiFamiliaService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
public class MiFamiliaServiceJPA implements IMiFamiliaService {
    @Autowired
    private MiFamiliaRepository repository;

    @Autowired
    private CatEscolaridadServiceJPA escolaridadServiceJPA;
    @Autowired
    private AlumnoServiceJPA alumnoService;

    @Autowired
    private CatInternetServiceJPA catInternetServiceJPA;

    @Autowired
    private DomicilioServiceJPA domicilioServiceJPA;

    @Autowired
    private MediosEstudiosServiceJPA mediosEstudiosServiceJPA;

    @Autowired
    private BienesHogarServiceJPA bienesHogarServiceJPA;

    @Autowired
    private PersonasDependientesServiceJPA personasDependientesServiceJPA;

    @Autowired
    private ViviendaFamiliarServiceJPA viviendaFamiliarServiceJPA;

    @Autowired
    private FechasRegistradasServiceJPA fechasRegistradasServiceJPA;

    @Autowired
    private ArchivoServiceJPA archivoServiceJPA;

    @PersistenceContext
    private EntityManager entityManager;

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
        Alumno alumno = model.getAlumno();
        if (!fechasRegistradasServiceJPA.permitirRegistro(alumno.getCarrera().getId()))
            throw new IllegalArgumentException("No es posible registrar tus datos en este momento. " +
                    "El periodo de registro para tu carrera no está activo actualmente.");
        return repository.save(model);
    }

    @Override
    @Transactional
    public MiFamilia create(Map<String, Object> params) throws Exception {
        MiFamilia model = new MiFamilia();
        try {
            Long idAlumno = JsonUtils.obtLong(params, "alumnoId");
            if (idAlumno == null) throw new IllegalArgumentException("El campo idAlumno es obligatorio");
            Alumno alumno = alumnoService.findById(idAlumno);
            model.setAlumno(alumno);
            this.build(params, model);
            model.setModuloCompleto(true);
            model = this.save(model);
            alumno.setMiFamilia(model);
            alumnoService.save(alumno); // Guardar el alumno con la referencia a MiFamilia
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir misDatos (create)");
        }
        return model;
    }

    @Override
    public MiFamilia update(MiFamilia model, Map<String, Object> params) throws Exception {
        return this.save(this.build(params, model));
    }

    @Override
    @Transactional
    public MiFamilia build(Map<String, Object> params, MiFamilia miFamilia) {
        try {
            String telefono = JsonUtils.obtString(params, "miFamilia.telefono");
            boolean tieneInternet = JsonUtils.obtBoolean(params, "vivienda.tieneInternet");
            Integer numHermanos = JsonUtils.obtInteger(params, "miFamilia.num_hermanos");
            Integer numHermanosEstudiando = JsonUtils.obtInteger(params, "miFamilia.num_hermanos_estudiando");
            Integer numHermanosNoEstudiando = JsonUtils.obtInteger(params, "miFamilia.num_hermanos_no_estudiando");
            Integer numHermanosLicenciatura = JsonUtils.obtInteger(params, "miFamilia.num_hermanos_licenciatura");
            Integer numPersonasDependen = JsonUtils.obtInteger(params, "miFamilia.num_personas_dependen");
            Long idEscolaridadPadre = JsonUtils.obtLong(params, "miFamilia.id_escolaridad_padre");
            Long idEscolaridadMadre = JsonUtils.obtLong(params, "miFamilia.id_escolaridad_madre");
            Long idCatInternet = JsonUtils.obtLong(params, "vivienda.id_cat_internet");

            if (telefono == null) throw new IllegalArgumentException("El campo telefono es obligatorio");
            if (numHermanos == null) throw new IllegalArgumentException("El campo numero de hermanos es obligatorio");
            if (numHermanosEstudiando == null)
                throw new IllegalArgumentException("El campo numero de hermanos estudiando es obligatorio");
            if (numHermanosNoEstudiando == null)
                throw new IllegalArgumentException("El campo numero de hermanos no estudiando es obligatorio");
            if (numHermanosLicenciatura == null)
                throw new IllegalArgumentException("El campo numero de hermanos licenciatura es obligatorio");
            if (numPersonasDependen == null)
                throw new IllegalArgumentException("El campo numero de personas que dependen es obligatorio");
            if (idEscolaridadPadre == null)
                throw new IllegalArgumentException("El campo escolaridad del padre es obligatorio");
            if (idEscolaridadMadre == null)
                throw new IllegalArgumentException("El campo escolaridad de la madre es obligatorio");
            if (idCatInternet == null) throw new IllegalArgumentException("El campo tipo de internet es obligatorio");

            miFamilia.setTelefono(telefono);
            miFamilia.setTieneInternet(tieneInternet);
            miFamilia.setNumHermanos(numHermanos);
            miFamilia.setNumHermanosEstudiando(numHermanosEstudiando);
            miFamilia.setNumHermanosNoEstudiando(numHermanosNoEstudiando);
            miFamilia.setNumHermanosLicenciatura(numHermanosLicenciatura);
            miFamilia.setNumPersonasDependen(numPersonasDependen);
            miFamilia.setEscolaridadPadre(escolaridadServiceJPA.findById(idEscolaridadPadre));
            miFamilia.setEscolaridadMadre(escolaridadServiceJPA.findById(idEscolaridadMadre));
            miFamilia.setCatInternet(catInternetServiceJPA.findById(idCatInternet));

            miFamilia = this.save(miFamilia); // Guardar miFamilia antes de agregar los servicios

            //construir y guardar la vivienda
            Map<String, Object> viviendaParams = (Map<String, Object>) params.get("vivienda");
            List<Map<String, Object>> serviciosViviendaParams = (List<Map<String, Object>>) params.get("serviciosVivienda");
            Map<String, Object> domicilioParams = (Map<String, Object>) params.get("domicilio");

            if (viviendaParams != null) {
                viviendaParams.put("serviciosVivienda", serviciosViviendaParams);
                viviendaParams.put("domicilio", domicilioParams);
                if (miFamilia.getViviendaFamiliar() == null) {
                    ViviendaFamiliar nuevaVivienda = viviendaFamiliarServiceJPA.create(viviendaParams, miFamilia);
                    miFamilia.setViviendaFamiliar(nuevaVivienda);
                } else {
                    miFamilia.setViviendaFamiliar(viviendaFamiliarServiceJPA.update(miFamilia.getViviendaFamiliar(), viviendaParams));
                }
            }

            // Construir y guardar el domicilio
            if (domicilioParams != null) {
                Long domicilioAlumnoID = domicilioParams.get("idDomicilioAlumno") != null ?
                        Long.valueOf(domicilioParams.get("idDomicilioAlumno").toString()) : null;

                if (domicilioAlumnoID == null) {
                    Domicilio domicilioAntiguo = miFamilia.getDomicilio();
                    if (domicilioAntiguo != null) {
                        // Si no hay ID, pero ya hay un domicilio asociado, lo desvinculamos
                        miFamilia.setDomicilio(null);
                        this.save(miFamilia); //
                        if (!domicilioServiceJPA.isDomicilioUsado(domicilioAntiguo.getId())) {
                            //eliminar domicilio antiguo
                            domicilioServiceJPA.deleteById(domicilioAntiguo.getId());
                        }
                    }
                    // agregar el nuevo domicilio
                    Domicilio nuevo = domicilioServiceJPA.create(domicilioParams);
                    miFamilia.setDomicilio(nuevo);
                } else {
                    System.out.println("hola");
                    Domicilio existente = domicilioServiceJPA.findById(domicilioAlumnoID);
                    Domicilio domicilioAntiguo = miFamilia.getDomicilio();
                    if (domicilioAntiguo != null && !domicilioAntiguo.equals(existente)) {
                        miFamilia.setDomicilio(null);
                        this.save(miFamilia); //
                        boolean enUso = domicilioServiceJPA.isDomicilioUsado(domicilioAntiguo.getId());
                        if (!enUso) {
                            domicilioServiceJPA.deleteById(domicilioAntiguo.getId());
                        }
                    }

                    miFamilia.setDomicilio(existente); // setea el nuevo
                }
            }

            List<Map<String, Object>> mediosEstudios = (List<Map<String, Object>>) params.get("mediosEstudio");
            if (mediosEstudios != null) {
                // Limpia la lista existente de mediosEstudio si ya existe
                if (miFamilia.getMediosEstudio() != null) {
                    miFamilia.getMediosEstudio().clear();
                } else {
                    miFamilia.setMediosEstudio(new ArrayList<>());
                }

                for (Map<String, Object> medioEstudioParams : mediosEstudios) {
                    Long idMedioEstudio = JsonUtils.obtLong(medioEstudioParams, "id_cat_medios_estudios");
                    if (idMedioEstudio != null) {
                        MediosEstudio mediosEstudio = mediosEstudiosServiceJPA.create(idMedioEstudio, miFamilia);
                        miFamilia.getMediosEstudio().add(mediosEstudio);
                    } else {
                        throw new IllegalArgumentException("El campo idMedioEstudio es obligatorio");
                    }
                }
            }


            List<Map<String, Object>> bienesHogar = (List<Map<String, Object>>) params.get("bienesHogar");
            if (bienesHogar != null) {
                if (miFamilia.getBienesHogar() != null) {
                    miFamilia.getBienesHogar().clear(); // desvincula elementos huérfanos
                } else {
                    miFamilia.setBienesHogar(new ArrayList<>());
                }

                for (Map<String, Object> bienHogarParams : bienesHogar) {
                    Long idBienHogar = JsonUtils.obtLong(bienHogarParams, "id_cat_bienes_hogar");
                    if (idBienHogar != null) {
                        BienesHogar bienHogar = bienesHogarServiceJPA.create(idBienHogar, miFamilia);
                        miFamilia.getBienesHogar().add(bienHogar);
                    } else {
                        throw new IllegalArgumentException("El campo idBienHogar es obligatorio");
                    }
                }
            }

            List<Map<String, Object>> personasDependientes = (List<Map<String, Object>>) params.get("personasDependientes");
            if (personasDependientes != null) {
                // Limpiar y eliminar huérfanos
                if (miFamilia.getPersonasDependientes() != null) {
                    for (PersonasDependientes pd : miFamilia.getPersonasDependientes()) {
                        if (pd.getRutaArchivo() != null) {
                            archivoServiceJPA.eliminarArchivo(pd.getRutaArchivo());
                        }
                    }
                    miFamilia.getPersonasDependientes().clear();
                    this.save(miFamilia); // para aplicar orphanRemoval
                } else {
                    miFamilia.setPersonasDependientes(new ArrayList<>());
                }

                for (Map<String, Object> personaDependienteParams : personasDependientes) {
                    Long id = personaDependienteParams.get("id") != null ?
                            Long.valueOf(personaDependienteParams.get("id").toString()) : null;

                    PersonasDependientes persona;
                    if (id != null) {
                        // Actualizar si existe
                        persona = personasDependientesServiceJPA.findById(id);
                        personasDependientesServiceJPA.update(persona, personaDependienteParams);
                    } else {
                        // Crear nueva si no tiene ID
                        persona = personasDependientesServiceJPA.create(personaDependienteParams, miFamilia);
                    }

                    miFamilia.getPersonasDependientes().add(persona);
                }
            }


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el alumno");
        }

        return miFamilia;
    }

    @Override
    public void deleteById(Long id) {
        MiFamilia model = this.findById(id);
        if (model != null) {
            repository.deleteById(id);
        }
    }

}
