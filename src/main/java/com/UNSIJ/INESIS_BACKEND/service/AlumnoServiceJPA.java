package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.AlumnoModel;
import com.UNSIJ.INESIS_BACKEND.repository.AlumnoRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IAlumnoService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AlumnoServiceJPA implements IAlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<AlumnoModel> findAll() {
        return alumnoRepository.findAll();
    }

    @Override
    public AlumnoModel findById(Long id) {
        return alumnoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Alumno no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public AlumnoModel save(AlumnoModel alumnoModel) throws Exception {
        return alumnoRepository.save(alumnoModel);
    }

    @Override
    public AlumnoModel create(Map<String, Object> params) throws Exception {
        AlumnoModel alumnoModel = new AlumnoModel();
        try {
            this.build(params, alumnoModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al crear el alumno");
        }
        return this.save(alumnoModel);
    }

    @Override
    public AlumnoModel update(AlumnoModel alumnoModel, Map<String, Object> params) throws Exception {
        try {
            this.build(params, alumnoModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al actualizar el alumno");
        }
        return this.save(alumnoModel);
    }

    @Override
    public AlumnoModel build(Map<String, Object> params, AlumnoModel alumnoModel) {
        try {
            // Aquí validamos y asignamos los campos que necesitamos del JSON
            String nombre = JsonUtils.obtString(params, "nombre");
            if (nombre == null) throw new IllegalArgumentException("El campo nombre es obligatorio");
            alumnoModel.setNombre(nombre);

            // Otros campos pueden ser agregados según la estructura del modelo
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el alumno");
        }
        return alumnoModel;
    }

    @Override
    public void deleteById(Long id) {
        AlumnoModel alumnoModel = this.findById(id);
        if (alumnoModel != null) {
            alumnoRepository.deleteById(id);
        }
    }
}
