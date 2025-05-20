package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.UsuarioModel;

public interface IUsuarioService {
    List<UsuarioModel> findAll();
    UsuarioModel findById(Long id);
    UsuarioModel save(UsuarioModel usuarioModel) throws Exception;
    UsuarioModel create(Map<String, Object> params) throws Exception;
    UsuarioModel update(UsuarioModel usuarioModel, Map<String, Object> params) throws Exception;
    UsuarioModel build(Map<String, Object> params, UsuarioModel usuarioModel) throws IllegalArgumentException;
    UsuarioModel updateInstance(UsuarioModel usuarioModel) throws Exception;
    void deleteById(Long id);
    UsuarioModel findByAlumnoId(Long idAlumno);

}
