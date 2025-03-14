package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;

import java.util.List;
import java.util.Map;


// aqui se definen los metodos a ocupar en serviceJPA
// los siguientes son los que deben ir siempre
//
public interface IEjemploService {
    List<Ejemplo> findAll();
    Ejemplo findById(Long id);
    Ejemplo save(Ejemplo ejemplo) throws Exception;
    Ejemplo create(Map<String, Object> params) throws Exception;
    Ejemplo update(Ejemplo ejemplo, Map<String, Object> params) throws Exception;
    Ejemplo build(Map<String, Object> params, Ejemplo ejemplo) throws IllegalArgumentException;
    Ejemplo updateInstance(Ejemplo ejemplo) throws Exception;
    void deleteById(Long id);
}
