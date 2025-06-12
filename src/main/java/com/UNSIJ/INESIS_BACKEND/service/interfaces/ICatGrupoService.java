package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.CatGrupo;

import java.util.List;
import java.util.Map;

public interface ICatGrupoService {
    List<CatGrupo> findAll();

    CatGrupo findById(Long id);

    CatGrupo save(CatGrupo catGrupo) throws Exception;

    CatGrupo create(Map<String, Object> params) throws Exception;

    CatGrupo update(CatGrupo catGrupo, Map<String, Object> params) throws Exception;

    CatGrupo build(Map<String, Object> params, CatGrupo catGrupo);

    CatGrupo updateInstance(CatGrupo catGrupoInstance) throws Exception;

    void deleteById(Long id);

    CatGrupo obtenerNombreGrupo(Long idCarrera, Long idSemestre);

}
