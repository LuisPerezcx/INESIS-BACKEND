package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.Domicilio;
import com.UNSIJ.INESIS_BACKEND.repository.DomicilioRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IDomicilioService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DomicilioServiceJPA implements IDomicilioService {
    @Autowired
    private DomicilioRepository domicilioRepository;

    @Override
    public List<Domicilio> findAll() {
        return domicilioRepository.findAll();
    }

    @Override
    public Domicilio findById(Long id) {
        return domicilioRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Domicilio no encontrado con el ID: " + id));
    }

    @Override
    @Transactional //SIEMPRE TRANSACTIONAL AQUI
    public Domicilio save(Domicilio domicilio) throws Exception {
        return domicilioRepository.save(domicilio);
    }


    @Override
    public Domicilio create(Map<String, Object> params) throws Exception {
        Domicilio domicilio = new Domicilio();
        try {
            this.build(params, domicilio);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el domicilio(create)");
        }
        return this.save(domicilio);
    }

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    public Domicilio update(Domicilio domicilio, Map<String, Object> params) throws Exception {
        try {
            this.build(params, domicilio);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el domicilio(update)");
        }
        return this.save(domicilio);
    }

    @Override
    public Domicilio build(Map<String, Object> params, Domicilio domicilio) {
        try {
            //domicilio.setEstado(JsonUtils.obtString(params, "estado"));
            //domicilio.setMunicipio(JsonUtils.obtString(params, "municipio"));
            domicilio.setLocalidad(JsonUtils.obtString(params, "localidad"));
            domicilio.setColonia(JsonUtils.obtString(params, "colonia"));
            domicilio.setCalle(JsonUtils.obtString(params, "calle"));
            domicilio.setNumero(JsonUtils.obtString(params, "numero"));
            domicilio.setCp(JsonUtils.obtString(params, "cp"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el domicilio(build)");
        }
        return domicilio;
    }

    //ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public Domicilio updateInstance(Domicilio domicilioInstance) throws Exception {
        Domicilio domicilioBD = this.findById(domicilioInstance.getIdDomicilio());
        //domicilioBD.setEstado(domicilioInstance.getEstado());
        //domicilioBD.setMunicipio(domicilioInstance.getMunicipio());
        domicilioBD.setLocalidad(domicilioInstance.getLocalidad());
        domicilioBD.setColonia(domicilioInstance.getColonia());
        domicilioBD.setCalle(domicilioInstance.getCalle());
        domicilioBD.setNumero(domicilioInstance.getNumero());
        domicilioBD.setCp(domicilioInstance.getCp());

        return this.save(domicilioBD);
    }

    @Override
    public void deleteById(Long id) {
        Domicilio domicilio = this.findById(id);
        if (domicilio != null) {
            domicilioRepository.deleteById(id);
        }
    }
}
