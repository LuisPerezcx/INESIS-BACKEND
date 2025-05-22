package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.AlumnoModel;
import com.UNSIJ.INESIS_BACKEND.model.UsuarioModel;
import com.UNSIJ.INESIS_BACKEND.repository.AlumnoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatCarreraRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatRolRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatSemestreRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatSexoRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IAlumnoService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlumnoServiceJPA implements IAlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CatCarreraServiceJPA carreraServiceJPA;

    @Autowired
    private CatSemestreServiceJPA semestreServiceJPA;

    @Autowired
    private CatSexoServiceJPA sexoServiceJPA;

    @Autowired
    private CatRolServiceJPA rolServiceJPA;

    @Autowired
    private CatGrupoServiceJPA grupoServiceJPA;

    @Autowired
    private UsuarioServiceJPA usuarioServiceJPA;

    @Override
    public List<AlumnoModel> findAll() {
        return alumnoRepository.findAll();
    }

    @Override
    public AlumnoModel findById(Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public AlumnoModel save(AlumnoModel alumnoModel) throws Exception {
        return alumnoRepository.save(alumnoModel);
    }

    @Override
    @Transactional
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
@Transactional
public AlumnoModel update(AlumnoModel alumnoModel, Map<String, Object> params) throws Exception {
    try {
        // Construir los nuevos valores para el alumno y su usuario
        this.build(params, alumnoModel);

    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        throw new IllegalArgumentException("Error al actualizar el alumno");
    }
    return save(alumnoModel);  // Guardar los cambios en el alumno
}


@Transactional
@Override
public AlumnoModel build(Map<String, Object> params, AlumnoModel alumnoModel) {
    try {
        // Asignar campos del alumno
        alumnoModel.setNombre(JsonUtils.obtString(params, "nombre"));
        alumnoModel.setApellido(JsonUtils.obtString(params, "apellido"));
        alumnoModel.setCurp(JsonUtils.obtString(params, "curp"));
        alumnoModel.setCorreo(JsonUtils.obtString(params, "correo"));
        alumnoModel.setTelefono(JsonUtils.obtString(params, "telefono"));
        alumnoModel.setMatricula(JsonUtils.obtString(params, "matricula"));

        Long idGrupo = JsonUtils.obtLong(params, "grupo");
        alumnoModel.setGrupo(grupoServiceJPA.findById(idGrupo));

        Long idCarrera = JsonUtils.obtLong(params, "carrera");
        alumnoModel.setCarrera(carreraServiceJPA.findById(idCarrera));

        Long idSemestre = JsonUtils.obtLong(params, "semestre");
        alumnoModel.setSemestre(semestreServiceJPA.findById(idSemestre));

        Long idSexo = JsonUtils.obtLong(params, "sexo");
        alumnoModel.setSexo(sexoServiceJPA.findById(idSexo));

        // Guardar los datos del alumno
        alumnoModel = save(alumnoModel);

        // Verificar si el alumno ya tiene un usuario
        if (alumnoModel.getUsuario() != null) {
            // Si ya existe un usuario, actualizamos su información
            Map<String, Object> usuarioParams = new HashMap<>();
            usuarioParams.put("usuario", JsonUtils.obtString(params, "usuario"));
            usuarioParams.put("contrasenia", JsonUtils.obtString(params, "contrasenia"));
            usuarioParams.put("estatus", params.getOrDefault("estatus", "Activo"));

            Long idRol = params.get("idCatRol") != null ? Long.parseLong(params.get("idCatRol").toString()) : 1L;  // Valor predeterminado
            Map<String, Object> rolMap = new HashMap<>();
            rolMap.put("idCatRol", idRol);
            usuarioParams.put("rol", rolMap);

            // Actualizar el usuario existente
            usuarioServiceJPA.update(alumnoModel.getUsuario(), usuarioParams);

        } else {
            // Si no existe un usuario, creamos uno nuevo
            Map<String, Object> usuarioParams = new HashMap<>();
            usuarioParams.put("usuario", JsonUtils.obtString(params, "usuario"));
            usuarioParams.put("contrasenia", JsonUtils.obtString(params, "contrasenia"));
            usuarioParams.put("estatus", params.getOrDefault("estatus", "Activo"));

            Long idRol = params.get("idCatRol") != null ? Long.parseLong(params.get("idCatRol").toString()) : 1L;  // Valor predeterminado
            Map<String, Object> rolMap = new HashMap<>();
            rolMap.put("idCatRol", idRol);
            usuarioParams.put("rol", rolMap);

            Map<String, Object> alumnoMap = new HashMap<>();
            alumnoMap.put("idAlumno", alumnoModel.getId());
            usuarioParams.put("alumno", alumnoMap);

            // Crear un nuevo usuario solo si no existe uno
            UsuarioModel usuario = usuarioServiceJPA.create(usuarioParams);

            // Relación bidireccional explícita
            usuario.setAlumno(alumnoModel);
            alumnoModel.setUsuario(usuario);
        }

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

    public boolean checkIfExists(String curp, String matricula, String correo) {
        return alumnoRepository.existsByCurp(curp) ||
                alumnoRepository.existsByMatricula(matricula) ||
                alumnoRepository.existsByCorreo(correo);
    }
}