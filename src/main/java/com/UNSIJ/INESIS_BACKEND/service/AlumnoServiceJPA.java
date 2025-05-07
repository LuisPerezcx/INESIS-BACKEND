package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.AlumnoModel;
import com.UNSIJ.INESIS_BACKEND.model.UsuarioModel;
import com.UNSIJ.INESIS_BACKEND.repository.AlumnoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatCarreraRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatRolRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatSemestreRepository;
import com.UNSIJ.INESIS_BACKEND.repository.CatSexoRepository;
import com.UNSIJ.INESIS_BACKEND.repository.UsuarioRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IAlumnoService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AlumnoServiceJPA implements IAlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CatRolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CatCarreraRepository carreraRepository;

    @Autowired
    private CatSemestreRepository semestreRepository;

    @Autowired
    private CatSexoRepository sexoRepository;


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
            String nombre = JsonUtils.obtString(params, "nombre");
            if (nombre == null)
                throw new IllegalArgumentException("El campo nombre es obligatorio");
            alumnoModel.setNombre(nombre);

            alumnoModel.setApellido(JsonUtils.obtString(params, "apellido"));
            alumnoModel.setCurp(JsonUtils.obtString(params, "curp"));
            alumnoModel.setCorreo(JsonUtils.obtString(params, "correo"));
            alumnoModel.setTelefono(JsonUtils.obtString(params, "telefono"));

            String matricula = JsonUtils.obtString(params, "matricula");
            alumnoModel.setMatricula(matricula);

            alumnoModel.setGrupo(JsonUtils.obtInteger(params, "grupo"));

            // Cargar catálogo
            alumnoModel.setCarrera(carreraRepository.findById(JsonUtils.obtLong(params, "carrera"))
                    .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada")));

            alumnoModel.setSemestre(semestreRepository.findById(JsonUtils.obtLong(params, "semestre"))
                    .orElseThrow(() -> new IllegalArgumentException("Semestre no encontrado")));

            alumnoModel.setSexo(sexoRepository.findById(JsonUtils.obtLong(params, "sexo"))
                    .orElseThrow(() -> new IllegalArgumentException("Sexo no encontrado")));

            // Crear y guardar usuario con contraseña cifrada
            UsuarioModel usuario = new UsuarioModel();
            usuario.setUsuario(matricula); // matrícula como username
            usuario.setContrasenia(passwordEncoder.encode("1234")); // CONTRASEÑA CIFRADA
            usuario.setEstatus("ACTIVO");

            usuario.setRol(rolRepository.findById(2L)
                    .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado")));

            usuario = usuarioRepository.save(usuario);
            alumnoModel.setUsuario(usuario);

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
