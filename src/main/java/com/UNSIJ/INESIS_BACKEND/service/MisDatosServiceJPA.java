package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.GastosIngresos;
import com.UNSIJ.INESIS_BACKEND.model.MisDatos;
import com.UNSIJ.INESIS_BACKEND.repository.CatCarreraRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatEstadoCivilRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatSemestreRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatSexoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.MisDatosRepository;
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
    private GastosIngresosJPA gastosIngresosJPA;

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
    @Transactional
    public MisDatos save(MisDatos misDatos) throws Exception {
        return misDatosRepository.save(misDatos);
    }

    @Override
    public MisDatos create(Map<String, Object> params) throws Exception {
        MisDatos misDatos = new MisDatos();
        try {
            // AQUI ASIGNAMOS VALORES QUE SOLO SE NECESITAN AL CREAR POR PRIMERA VEZ UN
            // REGISTRO
            // POR EJEMPLO EL CAMPO ACTIVO
            // misDatos.setCarrera("hola"); //ESTE ES UN CASO DE USO
            // AHORA LLAMAMOS AL METODO QUE SE OCUPA DE CONSTRUIR EL OBJETO
            this.build(params, misDatos);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir misDatos");
        }
        return this.save(misDatos);
    }

    @Override
    public MisDatos update(MisDatos misDatos, Map<String, Object> params) throws Exception {
        try {
            this.build(params, misDatos);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir a misDatos");
        }
        return this.save(misDatos);
    }

    @Override
    public MisDatos build(Map<String, Object> params, MisDatos misDatos) throws IllegalArgumentException {
        try {
            String nombreCompleto = JsonUtils.obtString(params, "nombreCompleto");
            if (nombreCompleto == null)
                throw new IllegalArgumentException("El campo nombre Completo es obligatorio");
            misDatos.setNombreCompleto(nombreCompleto);

            Long idCarrera = JsonUtils.obtLong(params, "idCarrera");
            if (idCarrera == null)
                throw new IllegalArgumentException("El campo idCarrera es obligatorio");
            misDatos.setCarrera(catCarreraRepository.findById(idCarrera)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró la carrera con ID: " + idCarrera)));

            Long idSemestre = JsonUtils.obtLong(params, "idSemestre");
            if (idSemestre == null)
                throw new IllegalArgumentException("El campo idSemestre es obligatorio");
            misDatos.setSemestre(catSemestreRepository.findById(idSemestre)
                    .orElseThrow(
                            () -> new IllegalArgumentException("No se encontró el semestre con ID: " + idSemestre)));

            Long idSexo = JsonUtils.obtLong(params, "idSexo");
            if (idSexo == null)
                throw new IllegalArgumentException("El campo idSexo es obligatorio");
            misDatos.setSexo(catSexoRepository.findById(idSexo)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró el sexo con ID: " + idSexo)));

            Long idEstadoCivil = JsonUtils.obtLong(params, "idEstadoCivil");
            if (idEstadoCivil == null)
                throw new IllegalArgumentException("El campo idEstadoCivil es obligatorio");
            misDatos.setEstadoCivil(catEstadoCivilRepository.findById(idEstadoCivil)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No se encontró el estado civil con ID: " + idEstadoCivil)));

            String recursosSuficientesString = JsonUtils.obtString(params, "recursosSuficientes"); 
            Boolean recursosSuficientes = null;
            if ("Si".equalsIgnoreCase(recursosSuficientesString)) {
                recursosSuficientes = true;
            } else if ("No".equalsIgnoreCase(recursosSuficientesString)) {
                recursosSuficientes = false;
            } else if (recursosSuficientesString != null) {
                throw new IllegalArgumentException("El valor de 'recursosSuficientes' debe ser 'Si' o 'No'.");
            }
            if (recursosSuficientes == null)
                throw new IllegalArgumentException("El campo recursos suficientes es obligatorio");
            misDatos.setRecursosSuficientes(recursosSuficientes);

            String familiarComuneroString = JsonUtils.obtString(params, "familiarComunero");
            Boolean familiarComunero = null;
            if ("Si".equalsIgnoreCase(familiarComuneroString)) {
                familiarComunero = true;
            } else if ("No".equalsIgnoreCase(familiarComuneroString)) {
                familiarComunero = false;
            } else if (familiarComuneroString != null) {
                throw new IllegalArgumentException("El valor de 'familiarComunero' debe ser 'Si' o 'No'.");   
            }
            if (familiarComunero == null)
                throw new IllegalArgumentException("El campo familiar comunero es obligatorio");
            misDatos.setFamiliarComunero(familiarComunero);

            String utilizaCelularString = JsonUtils.obtString(params, "utilizaCelular");
            Boolean utilizaCelular = JsonUtils.obtBoolean(params, "utilizaCelular");
            if (utilizaCelularString != null) {
                if ("Si".equalsIgnoreCase(utilizaCelularString)) {
                    utilizaCelular = true;
                } else if ("No".equalsIgnoreCase(utilizaCelularString)) {
                    utilizaCelular = false;
                } else {
                    throw new IllegalArgumentException("El valor de 'utilizaCelular' debe ser 'Si' o 'No'.");
                }
            }
            if (utilizaCelular == null)
                throw new IllegalArgumentException("El campo utiliza celular es obligatorio");
            misDatos.setUtilizaCelular(utilizaCelular);

            String tieneComputadoraString = JsonUtils.obtString(params, "tieneComputadora");
            Boolean tieneComputadora = JsonUtils.obtBoolean(params, "tieneComputadora");
            if (tieneComputadoraString != null) {
                if ("Si".equalsIgnoreCase(tieneComputadoraString)) {
                    tieneComputadora = true;
                } else if ("No".equalsIgnoreCase(tieneComputadoraString)) {
                    tieneComputadora = false;
                } else {
                    throw new IllegalArgumentException("El valor de 'tieneComputadora' debe ser 'Si' o 'No'.");
                }
            }
            if (tieneComputadora == null)
                throw new IllegalArgumentException("El campo tiene computadora es obligatorio");
            misDatos.setTieneComputadora(tieneComputadora);

            Map<String, Object> gastosIngresosParams = (Map<String, Object>) params.get("gastosIngresos");
            if(gastosIngresosParams != null) {
                GastosIngresos gastosIngresos = gastosIngresosJPA.create(gastosIngresosParams);
                misDatos.setGastosIngresos(gastosIngresos);
            }   

            String idioma = JsonUtils.obtString(params, "idioma");
            if (idioma == null)
                throw new IllegalArgumentException("El campo idioma es obligatorio");
            misDatos.setIdioma(idioma);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return misDatos;
    }

    @Override
    public MisDatos updateInstance(MisDatos misDatosInstance) throws Exception {
        MisDatos misDatosBD = this.findById(misDatosInstance.getId());
        misDatosBD.setCarrera(misDatosInstance.getCarrera());
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
