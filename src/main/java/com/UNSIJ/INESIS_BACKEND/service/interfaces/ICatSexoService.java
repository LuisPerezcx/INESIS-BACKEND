package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.CatSexo;

import java.util.List;
import java.util.Map;

public interface ICatSexoService {
    List<CatSexo> findAll();

    CatSexo findById(Long id);

    CatSexo save(CatSexo catSexo) throws Exception;

    CatSexo create(Map<String, Object> params) throws Exception;

    CatSexo update(CatSexo catSexo, Map<String, Object> params) throws Exception;

    CatSexo build(Map<String, Object> params, CatSexo catSexo) throws IllegalArgumentException;

    CatSexo updateInstance(CatSexo catSexo) throws Exception;

    void deleteById(Long id);
}
