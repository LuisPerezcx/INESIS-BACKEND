package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.Trabajo;
import com.UNSIJ.INESIS_BACKEND.repository.TrabajoRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ITrabajoService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class TrabajoServiceJPA implements ITrabajoService {
    @Autowired
    private TrabajoRepository trabajoRepository;

    @Override
    public List<Trabajo> findAll() {
        return trabajoRepository.findAll();
    }

    @Override
    public Trabajo findById(Long id) {
        return trabajoRepository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("Ejemplo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional //SIEMPRE TRANSACTIONAL AQUI
    public Trabajo save(Trabajo trabajo) throws Exception {
        return trabajoRepository.save(trabajo);
    }


    @Override
    public Trabajo create(Map<String, Object> params) throws Exception {
        Trabajo trabajo = new Trabajo();
        try {
            this.build(params, trabajo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(trabajo);
    }

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    public Trabajo update(Trabajo trabajo, Map<String, Object> params) throws Exception {
        try {
            this.build(params, trabajo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(trabajo);
    }

    @Override
    public Trabajo build(Map<String, Object> params, Trabajo trabajo){
        try {
            String nombreTrabajo = JsonUtils.obtString(params,"nombreTrabajo");
            trabajo.setNombreTrabajo(nombreTrabajo);

            Long telefonoTrabajo = JsonUtils.obtLong(params,"telefonoTrabajo");
            trabajo.setTelefonoTrabajo(telefonoTrabajo);

            Double ingresoMensual = JsonUtils.obtDouble(params,"ingresoMensual");
            trabajo.setIngresoMensual(ingresoMensual);

            String domicilioTrabajo = JsonUtils.obtString(params,"domicilioTrabajo");
            trabajo.setDomicilioTrabajo(domicilioTrabajo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return trabajo;
    }

    //ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public Trabajo updateInstance(Trabajo trabajoInstance) throws Exception {
        Trabajo trabajoBD = this.findById(trabajoInstance.getIdTrabajo());
        trabajoBD.setNombreTrabajo(trabajoInstance.getNombreTrabajo());
        trabajoBD.setTelefonoTrabajo(trabajoInstance.getTelefonoTrabajo());
        trabajoBD.setIngresoMensual(trabajoInstance.getIngresoMensual());
        trabajoBD.setDomicilioTrabajo(trabajoInstance.getDomicilioTrabajo());

        return this.save(trabajoBD);
    }

    @Override
    public void deleteById(Long id) {
        Trabajo trabajo = this.findById(id);
        if (trabajo!= null){
            trabajoRepository.deleteById(id);
        }
    }
}
