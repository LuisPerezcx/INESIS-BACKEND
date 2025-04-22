package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.MisDatos;
import com.UNSIJ.INESIS_BACKEND.repository.MisDatosRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IMisDatosService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class MisDatosServiceJPA implements IMisDatosService{
    @Autowired
    private MisDatosRepository misDatosRepository;

    @Override
    public List<MisDatos> findAll() {
        return misDatosRepository.findAll();
    }

    @Override
    public MisDatos findById(Long id) {
        return misDatosRepository.findById(id).orElseThrow( () ->
            new IllegalArgumentException("MisDatos no encontrado con el ID: " + id));
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
            //AQUI ASIGNAMOS VALORES QUE SOLO SE NECESITAN AL CREAR POR PRIMERA VEZ UN REGISTRO
            //POR EJEMPLO EL CAMPO ACTIVO
            misDatos.setCarrera("hola"); //ESTE ES UN CASO DE USO
            //AHORA LLAMAMOS AL METODO QUE SE OCUPA DE CONSTRUIR EL OBJETO
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
            String nombreCompleto = JsonUtils.obtString(params,"nombreCompleto");
            if(nombreCompleto == null) throw new IllegalArgumentException("El campo nombre Completo es obligatorio");
            misDatos.setNombreCompleto(nombreCompleto);

            String carrera = JsonUtils.obtString(params,"carrera");
            if(carrera == null) throw new IllegalArgumentException("El campo carrera es obligatorio");
            misDatos.setCarrera(carrera);

            String semestre = JsonUtils.obtString(params,"semestre");
            if(semestre == null) throw new IllegalArgumentException("El campo semestre es obligatorio");
            misDatos.setSemestre(semestre);

            Boolean recursosSuficientes = JsonUtils.obtBoolean(params,"recursosSuficientes");
            if(recursosSuficientes == null) throw new IllegalArgumentException("El campo recursosSuficientes es obligatorio");
            misDatos.setRecursosSuficientes(recursosSuficientes);

            Boolean familiarComunero = JsonUtils.obtBoolean(params,"familiarComunero");
            if(familiarComunero == null) throw new IllegalArgumentException("El campo familiarComunero es obligatorio");
            misDatos.setFamiliarComunero(familiarComunero);

            Boolean utilizaCelular = JsonUtils.obtBoolean(params,"utilizaCelular");
            if(utilizaCelular == null) throw new IllegalArgumentException("El campo utilizaCelular es obligatorio");
            misDatos.setUtilizaCelular(utilizaCelular);

            Boolean tieneComputadora = JsonUtils.obtBoolean(params,"tieneComputadora");
            if(tieneComputadora == null) throw new IllegalArgumentException("El campo tieneComputadora es obligatorio");
            misDatos.setTieneComputadora(tieneComputadora);

            String idioma = JsonUtils.obtString(params,"idioma");
            if(idioma == null) throw new IllegalArgumentException("El campo idioma es obligatorio");
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
        if (misDatos!= null){
            misDatosRepository.deleteById(id);
        }
    }
}
