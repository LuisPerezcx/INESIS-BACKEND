package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import com.UNSIJ.INESIS_BACKEND.model.MiTutor;

import java.util.List;
import java.util.Map;

public interface IMiTutorService {
    List<MiTutor> findAll();
    MiTutor findById(Long id);
    MiTutor save(MiTutor miTutor) throws Exception;
    MiTutor create(Map<String, Object> params) throws Exception;
    MiTutor update(MiTutor miTutor, Map<String, Object> params) throws Exception;
    MiTutor build(Map<String, Object> params, MiTutor miTutor) throws IllegalArgumentException;
    MiTutor updateInstance(MiTutor miTutor) throws Exception;
    void deleteById(Long id);
}
