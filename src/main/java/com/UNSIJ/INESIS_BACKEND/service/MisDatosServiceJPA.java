package com.UNSIJ.INESIS_BACKEND.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.*;
import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatSituacionViviendaModel;
import com.UNSIJ.INESIS_BACKEND.repository.*;
import com.UNSIJ.INESIS_BACKEND.repository.repositoryFamilia.CatSituacionViviendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.service.interfaces.IMisDatosService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class MisDatosServiceJPA implements IMisDatosService {
    @Autowired
    private MisDatosRepository misDatosRepository;

    @Autowired
    private CatCarreraRepository catCarreraRepository;

    @Autowired
    private CatSemestreRepository catSemestreRepository;

    @Autowired
    private CatSexoRepository catSexoRepository;

    @Autowired
    private CatEstadoCivilRepository catEstadoCivilRepository;

    @Autowired
    private GastosIngresosServiceJPA gastosIngresosServiceJPA;

    @Autowired
    private TransporteServiceJPA transporteServiceJPA;

    @Autowired
    private CatMediosTransporteService catMediosTransporteService;

    @Autowired
    private DomicilioServiceJPA domicilioServiceJPA;

    @Autowired
    private AlumnoServiceJPA alumnoService;

    @Autowired
    private CatSemestreServiceJPA semestreService;

    @Autowired
    private CatSituacionViviendaRepository catSituacionViviendaRepository;

    @Override
    public List<MisDatos> findAll() {
        return misDatosRepository.findAll();
    }

    @Override
    public MisDatos findById(Long id) {
        return misDatosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MisDatos no encontrado con el ID: " + id));
    }

    @Override
    public MisDatos findByAlumno(Long id) {
        return misDatosRepository.findByAlumno_Id(id)
                .orElseThrow(() -> new IllegalArgumentException("MisDatos no encontrado para el alumno con ID: " + id));
    }


    @Override
    @Transactional
    public MisDatos save(MisDatos misDatos) throws Exception {
        return misDatosRepository.save(misDatos);
    }

    @Override
    @Transactional
    public MisDatos create(Map<String, Object> params) throws Exception {
        MisDatos misDatos = new MisDatos();
        try {
            Long idAlumno = JsonUtils.obtLong(params, "alumnoId");
            if (idAlumno == null) throw new IllegalArgumentException("El campo idAlumno es obligatorio");
            Alumno alumno = alumnoService.findById(idAlumno);
            misDatos.setAlumno(alumno);
            this.build(params, misDatos);
            misDatos = this.save(misDatos);
            alumno.setMisDatos(misDatos);
            misDatos.setCompleto(true);
            alumnoService.save(alumno);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir misDatos (create)");
        }
        return misDatos;
    }

    @Override
    public MisDatos update(MisDatos misDatos, Map<String, Object> params) throws Exception {
        try {
            this.build(params, misDatos);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir a misDatos (update)");
        }
        return this.save(misDatos);
    }

    @Override
    @Transactional
    public MisDatos build(Map<String, Object> params, MisDatos misDatos) throws IllegalArgumentException {
        try {
            Long idAlumno = JsonUtils.obtLong(params, "alumnoId");
            if (idAlumno != null) {
                Alumno alumno = alumnoService.findById(idAlumno);
                Long idSemestre = JsonUtils.obtLong(params, "semestre");
                CatSemestre semestre = semestreService.findById(idSemestre);
                alumno.setSemestre(semestre);
                alumnoService.save(alumno);
            }


            Long idSexo = JsonUtils.obtLong(params, "sexo");
            if (idSexo == null)
                throw new IllegalArgumentException("El campo idSexo es obligatorio");
            misDatos.setSexo(catSexoRepository.findById(idSexo)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el sexo con ID: " + idSexo)));

            Long idEstadoCivil = JsonUtils.obtLong(params, "estadoCivil");
            if (idEstadoCivil == null)
                throw new IllegalArgumentException("El campo idEstadoCivil es obligatorio");
            misDatos.setEstadoCivil(catEstadoCivilRepository.findById(idEstadoCivil)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No se encontró el estado civil con ID: " + idEstadoCivil)));

            misDatos.setRecursosSuficientes(JsonUtils.parseBooleanFlexible(params.get("recursosSuficientes"), "recursosSuficientes"));
            misDatos.setFamiliarComunero(JsonUtils.parseBooleanFlexible(params.get("familiarComunero"), "familiarComunero"));
            misDatos.setUtilizaCelular(JsonUtils.parseBooleanFlexible(params.get("utilizaCelular"), "utilizaCelular"));
            misDatos.setTieneComputadora(JsonUtils.parseBooleanFlexible(params.get("tieneComputadora"), "tieneComputadora"));
            misDatos.setLlevaAutomovil(JsonUtils.parseBooleanFlexible(params.get("llevaAutomovil"), "llevaAutomovil"));
            misDatos.setLlevamotocicleta(JsonUtils.parseBooleanFlexible(params.get("llevaMotocicleta"), "llevaMotocicleta"));

            Map<String, Object> gastosIngresosParams = (Map<String, Object>) params.get("gastosIngresos");
            if (gastosIngresosParams != null) {
                if (misDatos.getGastosIngresos() != null) {
                    misDatos.setGastosIngresos(gastosIngresosServiceJPA.update(misDatos.getGastosIngresos(), gastosIngresosParams));
                } else {
                    GastosIngresos gastosIngresos = gastosIngresosServiceJPA.create(gastosIngresosParams);
                    misDatos.setGastosIngresos(gastosIngresos);
                }
            }

            Map<String, Object> transporteAutomovilParams = (Map<String, Object>) params.get("transporteAutomovil");
            if (misDatos.getLlevaAutomovil() && transporteAutomovilParams != null &&
                    transporteAutomovilParams.values().stream().anyMatch(v -> v != null && !v.toString().trim().isEmpty())) {
                if (misDatos.getTransporteAutomovil() != null) {
                    misDatos.setTransporteAutomovil(transporteServiceJPA.update(misDatos.getTransporteAutomovil(), transporteAutomovilParams));
                } else {
                    Transporte transporteCarro = transporteServiceJPA.create(transporteAutomovilParams);
                    misDatos.setTransporteAutomovil(transporteCarro);
                }
            }

            Map<String, Object> transporteMotocicletaParams = (Map<String, Object>) params.get("transporteMotocicleta");
            if (misDatos.getLlevamotocicleta() && transporteMotocicletaParams != null &&
                    transporteMotocicletaParams.values().stream().anyMatch(v -> v != null && !v.toString().trim().isEmpty())) {
                if (misDatos.getTransporteMotocicleta() != null) {
                    misDatos.setTransporteMotocicleta(transporteServiceJPA.update(misDatos.getTransporteMotocicleta(), transporteMotocicletaParams));
                } else {
                    Transporte transporteMotocicleta = transporteServiceJPA.create(transporteMotocicletaParams);
                    misDatos.setTransporteMotocicleta(transporteMotocicleta);
                }
            }


            List<Map<String, Object>> mediosTrasladoParams = (List<Map<String, Object>>) params.get("mediosTraslado");
            List<MedioTraslado> mediosTraslado = new ArrayList<>();
            for (Map<String, Object> item : mediosTrasladoParams) {
                Map<String, Object> catTransporteMap = (Map<String, Object>) item.get("catMediosTransporte");

                Long idCatMedio = Long.valueOf(catTransporteMap.get("id").toString());

                MedioTraslado medio = new MedioTraslado();
                medio.setCatMediosTransporte(catMediosTransporteService.findById(idCatMedio));
                medio.setMisDatos(misDatos); // Relación bidireccional
                mediosTraslado.add(medio);
            }
            misDatos.getMediosTraslado().clear();
            misDatos.getMediosTraslado().addAll(mediosTraslado);

            String idioma = JsonUtils.obtString(params, "idioma");
            if (idioma == null)
                throw new IllegalArgumentException("El campo idioma es obligatorio");
            misDatos.setIdioma(idioma);

            Long idSituacionVivienda = JsonUtils.obtLong(params, "situacionVivienda");
            System.out.println("Situacion vivienda: " + idSituacionVivienda);
            if (idSituacionVivienda == null)
                throw new IllegalArgumentException("El campo 'situacionVivienda' es obligatorio");
            CatSituacionViviendaModel cat = catSituacionViviendaRepository.findById(idSituacionVivienda)
                    .orElseThrow(() -> new IllegalArgumentException("Situación de vivienda no encontrada"));
            misDatos.setSituacionVivienda(cat);

            String nombreCasaHuesped = JsonUtils.obtString(params, "nombreCasaHuesped");
            if (nombreCasaHuesped == null)
                throw new IllegalArgumentException("El campo nombre casa huespedes es obligatorio");
            misDatos.setNombreCasaHuesped(nombreCasaHuesped);

            misDatos = this.save(misDatos);
            Map<String, Object> domicilioParams = (Map<String, Object>) params.get("domicilio");
            if (domicilioParams != null) {
                if(misDatos.getDomicilio() != null) {
                    misDatos.setDomicilio(domicilioServiceJPA.update(misDatos.getDomicilio(), domicilioParams));
                } else {
                    Domicilio domicilio = domicilioServiceJPA.create(domicilioParams);
                    misDatos.setDomicilio(domicilio);
                }
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el misDatos (build)");
        }
        return misDatos;
    }

    @Override
    public MisDatos updateInstance(MisDatos misDatosInstance) throws Exception {
        MisDatos misDatosBD = this.findById(misDatosInstance.getId());
        //misDatosBD.setCarrera(misDatosInstance.getCarrera());
        return this.save(misDatosBD);
    }

    @Override
    public void deleteById(Long id) {
        MisDatos misDatos = this.findById(id);
        if (misDatos != null) {
            misDatosRepository.deleteById(id);
        }
    }
}
