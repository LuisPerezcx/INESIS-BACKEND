package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CatMediosTransporte;
import com.UNSIJ.INESIS_BACKEND.repository.CatMediosTransporteRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatMediosTransporteService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CatMediosTransporteService implements ICatMediosTransporteService {
    @Autowired
    private CatMediosTransporteRepository catMediosTransporteRepository;

    @Override
    public List<CatMediosTransporte> findAll() {
        return catMediosTransporteRepository.findAll();
    }

    @Override
    public CatMediosTransporte findById(Long id) {
        return catMediosTransporteRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("CatMedioTransporte no encontrado con el ID: " + id));
    }

    @Override
    @Transactional //SIEMPRE TRANSACTIONAL AQUI
    public CatMediosTransporte save(CatMediosTransporte catMediosTransporte) throws Exception {
        return catMediosTransporteRepository.save(catMediosTransporte);
    }


    @Override
    public CatMediosTransporte create(Map<String, Object> params) throws Exception {
        CatMediosTransporte catMediosTransporte = new CatMediosTransporte();
        try {
            this.build(params, catMediosTransporte);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el transporte(create)");
        }
        return this.save(catMediosTransporte);
    }

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    public CatMediosTransporte update(CatMediosTransporte catMediosTransporte, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catMediosTransporte);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el transporte(update)");
        }
        return this.save(catMediosTransporte);
    }

    @Override
    public CatMediosTransporte build(Map<String, Object> params, CatMediosTransporte catMediosTransporte) {
        try {
            catMediosTransporte.setNombreMedio(JsonUtils.obtString(params, "nombre_medio"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el transporte(build)");
        }
        return catMediosTransporte;
    }

    //ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public CatMediosTransporte updateInstance(CatMediosTransporte catMediosTransporteInstance) throws Exception {
        CatMediosTransporte catMediosTransporteBD = this.findById(catMediosTransporteInstance.getId());
        catMediosTransporteBD.setNombreMedio(catMediosTransporteInstance.getNombreMedio());
        return this.save(catMediosTransporteBD);
    }

    @Override
    public void deleteById(Long id) {
        CatMediosTransporte catMediosTransporte = this.findById(id);
        if (catMediosTransporte != null) {
            catMediosTransporteRepository.deleteById(id);
        }
    }
}
