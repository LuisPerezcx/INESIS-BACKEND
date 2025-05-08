package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import java.util.List;
import java.util.Map;
import com.UNSIJ.INESIS_BACKEND.model.CatGrupoModel;

public interface ICatGrupoService {
    List<CatGrupoModel> findAll();
    CatGrupoModel findById(Long id);
    CatGrupoModel save(CatGrupoModel catGrupoModel) throws Exception;
    CatGrupoModel create(Map<String, Object> params) throws Exception;
    CatGrupoModel update(CatGrupoModel catGrupoModel, Map<String, Object> params) throws Exception;
    CatGrupoModel build(Map<String, Object> params, CatGrupoModel catGrupoModel);
    CatGrupoModel updateInstance(CatGrupoModel catGrupoInstance) throws Exception;
    void deleteById(Long id);
    String obtenerNombreGrupo(Long idCarrera, Long idSemestre);

}
