package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.UsuarioModel;
import com.UNSIJ.INESIS_BACKEND.repository.UsuarioRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IUsuarioService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class UsuarioServiceJPA implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CatRolServiceJPA rolServiceJPA;

    @Override
    public List<UsuarioModel> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioModel findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public UsuarioModel save(UsuarioModel usuarioModel) throws Exception {
        return usuarioRepository.save(usuarioModel);
    }

    @Override
    public UsuarioModel create(Map<String, Object> params) throws Exception {
        UsuarioModel usuarioModel = new UsuarioModel();
        try {
            this.build(params, usuarioModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el usuario");
        }
        return this.save(usuarioModel);
    }

    @Override
    public UsuarioModel update(UsuarioModel usuarioModel, Map<String, Object> params) throws Exception {
        try {
            this.build(params, usuarioModel);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el usuario");
        }
        return this.save(usuarioModel);
    }

    @Override
    @Transactional
    public UsuarioModel build(Map<String, Object> params, UsuarioModel usuarioModel) {
        try {
            String usuario = JsonUtils.obtString(params, "usuario");
            if (usuario == null)
                throw new IllegalArgumentException("El campo usuario es obligatorio");
            usuarioModel.setUsuario(usuario);

            String contrasenia = JsonUtils.obtString(params, "contrasenia");
            if (contrasenia == null)
                throw new IllegalArgumentException("El campo contraseña es obligatorio");
            usuarioModel.setContrasenia(contrasenia);

            Boolean estatus = JsonUtils.obtBoolean(params, "estatus");
            if (estatus == null)
                throw new IllegalArgumentException("El campo estatus es obligatorio");
            usuarioModel.setEstatus(estatus);

            Map<String, Object> rolMap = (Map<String, Object>) params.get("rol");
            if (rolMap == null || rolMap.get("idCatRol") == null) {
                throw new IllegalArgumentException("El campo rol.idCatRol es obligatorio");
            }

            Long idRol = Long.parseLong(rolMap.get("idCatRol").toString());
            usuarioModel.setRol(rolServiceJPA.findById(idRol));

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir el usuario");
        }
        return usuarioModel;
    }

    @Override
    public UsuarioModel updateInstance(UsuarioModel usuarioInstance) throws Exception {
        UsuarioModel usuarioBD = this.findById(usuarioInstance.getId());
        usuarioBD.setUsuario(usuarioInstance.getUsuario());
        usuarioBD.setContrasenia(usuarioInstance.getContrasenia());
        usuarioBD.setEstatus(usuarioInstance.getEstatus());
        return this.save(usuarioBD);
    }

    @Override
    public void deleteById(Long id) {
        UsuarioModel usuarioModel = this.findById(id);
        if (usuarioModel != null) {
            usuarioRepository.deleteById(id);
        }
    }

    @Override
    public UsuarioModel findByAlumnoId(Long idAlumno) {
        return usuarioRepository.findByAlumnoId(idAlumno)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró un usuario para el alumno con ID: " + idAlumno));
    }

    public UsuarioModel validarLogin(String usuario, String contrasenia) {
        UsuarioModel user = usuarioRepository.findByUsuario(usuario)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    
        if (!user.getContrasenia().equals(contrasenia)) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
    
        return user;
    }
}
