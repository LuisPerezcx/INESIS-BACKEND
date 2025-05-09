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

    @Override
    public List<UsuarioModel> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioModel findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> 
                new IllegalArgumentException("Usuario no encontrado con el ID: " + id));
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
            if (usuario == null) throw new IllegalArgumentException("El campo usuario es obligatorio");
            usuarioModel.setUsuario(usuario);

            String contrasenia = JsonUtils.obtString(params, "contrasenia");
            if (contrasenia == null) throw new IllegalArgumentException("El campo contraseña es obligatorio");
            usuarioModel.setContrasenia(contrasenia);

            String estatus = JsonUtils.obtString(params, "estatus");
            if (estatus == null) throw new IllegalArgumentException("El campo estatus es obligatorio");
            usuarioModel.setEstatus(estatus);

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

}
