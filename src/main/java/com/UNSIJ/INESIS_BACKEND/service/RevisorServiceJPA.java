package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Revisor;
import com.UNSIJ.INESIS_BACKEND.model.Usuario;
import com.UNSIJ.INESIS_BACKEND.repository.RevisorRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IRevisorService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RevisorServiceJPA implements IRevisorService {

    @Autowired
    private RevisorRepository revisorRepository;

    @Autowired
    private UsuarioServiceJPA usuarioServiceJPA;

    @Override
    public List<Revisor> findAll() {
        return revisorRepository.findAll();
    }

    @Override
    public Revisor findById(Long id) {
        return revisorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Revisor no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public Revisor save(Revisor revisor) throws Exception {
        return revisorRepository.save(revisor);
    }

    @Override
    @Transactional
    public Revisor create(Map<String, Object> params) throws Exception {
        Revisor revisor = new Revisor();
        try {
            this.build(params, revisor);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al crear el revisor");
        }
        return this.save(revisor);
    }

    @Override
    @Transactional
    public Revisor update(Revisor revisor, Map<String, Object> params) throws Exception {
        try {
            this.build(params, revisor);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al actualizar el revisor");
        }
        return save(revisor);
    }

    @Transactional
    @Override
    public Revisor build(Map<String, Object> params, Revisor revisor) {
        try {
            revisor.setNombre(JsonUtils.obtString(params, "nombre"));
            revisor.setApellidoPaterno(JsonUtils.obtString(params, "apellidoPaterno"));
            revisor.setApellidoMaterno(JsonUtils.obtString(params, "apellidoMaterno"));
            revisor.setMatricula(JsonUtils.obtString(params, "matricula"));
            revisor.setDepartamento(JsonUtils.obtString(params, "departamento"));

            // Guardar Revisor
            revisor = save(revisor);

            // Crear o actualizar usuario asociado
            Long idUsuario = JsonUtils.obtLong(params, "idUsuario");

            if (idUsuario != null) {
                // Actualizar usuario
                Usuario usuario = usuarioServiceJPA.findById(idUsuario);
                Map<String, Object> usuarioParams = new HashMap<>();
                usuarioParams.put("usuario", JsonUtils.obtString(params, "usuario"));
                usuarioParams.put("contrasenia", JsonUtils.obtString(params, "contrasenia"));
                usuarioParams.put("estatus", params.getOrDefault("estatus", "Activo"));
                usuarioServiceJPA.update(usuario, usuarioParams);
            } else {
                // Crear nuevo usuario
                Map<String, Object> usuarioParams = new HashMap<>();
                usuarioParams.put("usuario", JsonUtils.obtString(params, "usuario"));
                usuarioParams.put("contrasenia", JsonUtils.obtString(params, "contrasenia"));
                usuarioParams.put("estatus", params.getOrDefault("estatus", "Activo"));

                Long idRol = params.get("idCatRol") != null ? Long.parseLong(params.get("idCatRol").toString()) : 2L; // Suponiendo
                                                                                                                      // rol
                                                                                                                      // 2
                                                                                                                      // para
                                                                                                                      // revisor
                Map<String, Object> rolMap = new HashMap<>();
                rolMap.put("idCatRol", idRol);
                usuarioParams.put("rol", rolMap);

                Usuario nuevoUsuario = usuarioServiceJPA.create(usuarioParams);
                revisor.setUsuario(nuevoUsuario);
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el revisor");
        }
        return revisor;
    }

    @Override
    public void deleteById(Long id) {
        Revisor revisor = this.findById(id);
        if (revisor != null) {
            revisorRepository.deleteById(id);
        }
    }

    public boolean checkIfExists(String matricula) {
        return revisorRepository.existsByMatricula(matricula);
    }

}
