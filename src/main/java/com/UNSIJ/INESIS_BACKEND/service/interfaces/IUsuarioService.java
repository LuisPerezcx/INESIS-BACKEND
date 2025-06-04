package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;

import com.UNSIJ.INESIS_BACKEND.model.Usuario;

public interface IUsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuarioModel) throws Exception;
    Usuario create(Map<String, Object> params) throws Exception;
    Usuario update(Usuario usuarioModel, Map<String, Object> params) throws Exception;
    Usuario build(Map<String, Object> params, Usuario usuarioModel) throws IllegalArgumentException;
    Usuario updateInstance(Usuario usuarioModel) throws Exception;
    void deleteById(Long id);
    Usuario findByAlumnoId(Long idAlumno);

}
