package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.model.UsuarioModel;
import com.UNSIJ.INESIS_BACKEND.repository.AlumnoRepository;
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
    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno findById(Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public Alumno save(Alumno alumno) throws Exception {
        return alumnoRepository.save(alumno);
    }

    @Override
    @Transactional
    public Alumno create(Map<String, Object> params) throws Exception {
        Alumno alumno = new Alumno();
        try {
            this.build(params, alumno);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al crear el alumno");
        }
        return this.save(alumno);
    }

@Override
@Transactional
public Alumno update(Alumno alumno, Map<String, Object> params) throws Exception {
    try {
        // Construir los nuevos valores para el alumno y su usuario
        this.build(params, alumno);

    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        throw new IllegalArgumentException("Error al actualizar el alumno");
    }
    return save(alumno);  // Guardar los cambios en el alumno
}


@Transactional
@Override
public Alumno build(Map<String, Object> params, Alumno alumno) {
    try {
        // Asignar campos del alumno
        alumno.setNombre(JsonUtils.obtString(params, "nombre"));
        alumno.setApellido(JsonUtils.obtString(params, "apellido"));
        alumno.setCurp(JsonUtils.obtString(params, "curp"));
        alumno.setCorreo(JsonUtils.obtString(params, "correo"));
        alumno.setTelefono(JsonUtils.obtString(params, "telefono"));
        alumno.setMatricula(JsonUtils.obtString(params, "matricula"));

        Long idGrupo = JsonUtils.obtLong(params, "grupo");
        alumno.setGrupo(grupoServiceJPA.findById(idGrupo));

        Long idCarrera = JsonUtils.obtLong(params, "carrera");
        alumno.setCarrera(carreraServiceJPA.findById(idCarrera));

        Long idSemestre = JsonUtils.obtLong(params, "semestre");
        alumno.setSemestre(semestreServiceJPA.findById(idSemestre));

        Long idSexo = JsonUtils.obtLong(params, "sexo");
        alumno.setSexo(sexoServiceJPA.findById(idSexo));

        // Guardar los datos del alumno
        alumno = save(alumno);

        // Verificar si el alumno ya tiene un usuario
        if (alumno.getUsuario() != null) {
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
            usuarioServiceJPA.update(alumno.getUsuario(), usuarioParams);

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
            alumnoMap.put("idAlumno", alumno.getId());
            usuarioParams.put("alumno", alumnoMap);

            // Crear un nuevo usuario solo si no existe uno
            UsuarioModel usuario = usuarioServiceJPA.create(usuarioParams);

            // Relación bidireccional explícita
            usuario.setAlumno(alumno);
            alumno.setUsuario(usuario);
        }

    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        throw new IllegalArgumentException("Error al construir el alumno");
    }
    return alumno;
}

    @Override
    public void deleteById(Long id) {
        Alumno alumno = this.findById(id);
        if (alumno != null) {
            alumnoRepository.deleteById(id);
        }
    }

    public boolean checkIfExists(String curp, String matricula, String correo) {
        return alumnoRepository.existsByCurp(curp) ||
                alumnoRepository.existsByMatricula(matricula) ||
                alumnoRepository.existsByCorreo(correo);
    }
}