package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.CatTipoTransporte;
import com.UNSIJ.INESIS_BACKEND.repository.CatTipoTransporteRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ICatTipoTransporteService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class CatTipoTransporteServiceJPA implements ICatTipoTransporteService {
    @Autowired
    private CatTipoTransporteRepository catTipoTransporteRepository;

    @Override
    public List<CatTipoTransporte> findAll() {
        return catTipoTransporteRepository.findAll();
    }

    @Override
    public CatTipoTransporte findById(Long id) {
        return catTipoTransporteRepository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("CatTipoTransporte no encontrado con el ID: " + id));
    }

    @Override
    @Transactional //SIEMPRE TRANSACTIONAL AQUI
    public CatTipoTransporte save(CatTipoTransporte catTipoTransporte) throws Exception {
        return catTipoTransporteRepository.save(catTipoTransporte);
    }


    @Override
    public CatTipoTransporte create(Map<String, Object> params) throws Exception {
        CatTipoTransporte catTipoTransporte = new CatTipoTransporte();
        try {
            this.build(params, catTipoTransporte);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(catTipoTransporte);
    }

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    public CatTipoTransporte update(CatTipoTransporte catTipoTransporte, Map<String, Object> params) throws Exception {
        try {
            this.build(params, catTipoTransporte);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(catTipoTransporte);
    }

    @Override
    public CatTipoTransporte build(Map<String, Object> params, CatTipoTransporte catTipoTransporte){
        try {
            String  nombreTipo = JsonUtils.obtString(params, "nombreTipo");
            catTipoTransporte.setNombreTipo(nombreTipo);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return catTipoTransporte;
    }

    //ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public CatTipoTransporte updateInstance(CatTipoTransporte catTipoTransporteInstance) throws Exception {
        CatTipoTransporte catTipoTransporteBD = this.findById(catTipoTransporteInstance.getIdCatTipoTransporte());
        catTipoTransporteBD.setNombreTipo(catTipoTransporteInstance.getNombreTipo());
        return this.save(catTipoTransporteBD);
    }

    @Override
    public void deleteById(Long id) {
        CatTipoTransporte catTipoTransporte = this.findById(id);
        if (catTipoTransporte!= null){
            catTipoTransporteRepository.deleteById(id);
        }
    }
}
