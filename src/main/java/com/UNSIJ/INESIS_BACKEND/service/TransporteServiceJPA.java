package com.UNSIJ.INESIS_BACKEND.service;

import com.UNSIJ.INESIS_BACKEND.model.CatTipoTransporte;
import com.UNSIJ.INESIS_BACKEND.model.Transporte;
import com.UNSIJ.INESIS_BACKEND.repository.CatTipoTransporteRepository;
import com.UNSIJ.INESIS_BACKEND.repository.TransporteRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.ITransporteService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class TransporteServiceJPA implements ITransporteService {
    @Autowired
    private TransporteRepository transporteRepository;

    @Autowired
    private CatTipoTransporteRepository catTipoTransporteRepository;

    @Override
    public List<Transporte> findAll() {
        return transporteRepository.findAll();
    }

    @Override
    public Transporte findById(Long id) {
        return transporteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transporte no encontrado con el ID: " + id));
    }

    @Override
    @Transactional // SIEMPRE TRANSACTIONAL AQUI
    public Transporte save(Transporte transporte) throws Exception {
        return transporteRepository.save(transporte);
    }

    @Override
    public Transporte create(Map<String, Object> params) throws Exception {
        Transporte transporte = new Transporte();
        try {
            this.build(params, transporte);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el trnasporte (create)");
        }
        return this.save(transporte);
    }

    // ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN
    // MAPA(JSON)
    @Override
    public Transporte update(Transporte transporte, Map<String, Object> params) throws Exception {
        try {
            this.build(params, transporte);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el transporte (update)");
        }
        return this.save(transporte);
    }

    @Override
    public Transporte build(Map<String, Object> params, Transporte transporte) {
        try {
            String marca = JsonUtils.obtString(params, "marca");
            if (marca == null || marca.isEmpty())
                throw new IllegalArgumentException("El campo 'marca' es obligatorio.");
            transporte.setMarca(marca);

            String modelo = JsonUtils.obtString(params, "modelo");
            if (modelo == null || modelo.isEmpty())
                throw new IllegalArgumentException("El campo 'modelo' es obligatorio.");
            transporte.setModelo(modelo);

            Integer anio = JsonUtils.obtInteger(params, "anio");
            if (anio == null)
                throw new IllegalArgumentException("El campo 'anio' es obligatorio.");
            transporte.setAnio(anio);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el transporte (build)");
        }
        return transporte;
    }

    // ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public Transporte updateInstance(Transporte transporteInstance) throws Exception {
        Transporte transporteBD = this.findById(transporteInstance.getIdTransporte());

        transporteBD.setMarca(transporteInstance.getMarca());
        transporteBD.setModelo(transporteInstance.getModelo());
        transporteBD.setAnio(transporteInstance.getAnio());

        return this.save(transporteBD);
    }

    @Override
    public void deleteById(Long id) {
        Transporte transporte = this.findById(id);
        if (transporte != null) {
            transporteRepository.deleteById(id);
        }
    }
}
