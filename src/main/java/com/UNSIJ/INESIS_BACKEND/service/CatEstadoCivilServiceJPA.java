package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.CatEstadoCivil;
import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import com.UNSIJ.INESIS_BACKEND.repository.CatEstadoCivilRepository;
import com.UNSIJ.INESIS_BACKEND.repository.EjemploRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatEstadoCivilService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class CatEstadoCivilServiceJPA implements ICatEstadoCivilService {
    @Autowired
    private CatEstadoCivilRepository catEstadoCivilRepository;

    @Override
    public List<CatEstadoCivil> findAll() {
        return catEstadoCivilRepository.findAll();
    }

    @Override
    public CatEstadoCivil findById(Long id) {
        return catEstadoCivilRepository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("Ejemplo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional //SIEMPRE TRANSACTIONAL AQUI
    public CatEstadoCivil save(CatEstadoCivil catEstadoCivil) throws Exception {
        return catEstadoCivilRepository.save(catEstadoCivil);
    }


    @Override
    public CatEstadoCivil create(Map<String, Object> params) throws Exception {
        CatEstadoCivil catEstadoCivil = new CatEstadoCivil();
        try {
            this.build(params, catEstadoCivil);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(catEstadoCivil);
    }

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    public CatEstadoCivil update(CatEstadoCivil catEstadoCivil, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catEstadoCivil);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(catEstadoCivil);
    }

    @Override
    public CatEstadoCivil build(Map<String, Object> params, CatEstadoCivil catEstadoCivil){
        try {
            String nombreEstadoCivil = JsonUtils.obtString(params, "nombreEstadoCivil");
            if (nombreEstadoCivil == null || nombreEstadoCivil.isEmpty()) {
                throw new IllegalArgumentException("El nombre del estado civil no puede estar vacío");
            }
            catEstadoCivil.setNombreEstadoCivil(nombreEstadoCivil);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return catEstadoCivil;
    }

    //ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public CatEstadoCivil updateInstance(CatEstadoCivil catEstadoCivilInstance) throws Exception {
        CatEstadoCivil catEstadoCivilBD = this.findById(catEstadoCivilInstance.getId());
        catEstadoCivilBD.setNombreEstadoCivil(catEstadoCivilInstance.getNombreEstadoCivil());
        return this.save(catEstadoCivilBD);
    }

    @Override
    public void deleteById(Long id) {
        CatEstadoCivil catEstadoCivil = this.findById(id);
        if (catEstadoCivil!= null){
            catEstadoCivilRepository.deleteById(id);
        }
    }
}
