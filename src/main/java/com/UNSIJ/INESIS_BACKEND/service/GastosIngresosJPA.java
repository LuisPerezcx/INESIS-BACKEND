package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.GastosIngresos;
import com.UNSIJ.INESIS_BACKEND.repository.GastosIngresosRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IGastosIngresosService;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class GastosIngresosJPA implements IGastosIngresosService {
    @Autowired
    private GastosIngresosRepository gastosIngresosRepository;

    @Override
    public List<GastosIngresos> findAll() {
        return gastosIngresosRepository.findAll();
    }

    @Override
    public GastosIngresos findById(Long id) {
        return gastosIngresosRepository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("Ejemplo no encontrado con el ID: " + id));
    }

    @Override
    @Transactional //SIEMPRE TRANSACTIONAL AQUI
    public GastosIngresos save(GastosIngresos gastosIngresos) throws Exception {
        return gastosIngresosRepository.save(gastosIngresos);
    }


    @Override
    public GastosIngresos create(Map<String, Object> params) throws Exception {
        GastosIngresos gastosIngresos = new GastosIngresos();
        try {
            this.build(params, gastosIngresos);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(gastosIngresos);
    }

    //ESTE METODO SE OCUPA AL ACTUALIZAR DESDE EL FRONTEND YA QUE RECIBE UN MAPA(JSON)
    @Override
    public GastosIngresos update(GastosIngresos gastosIngresos, Map<String, Object> params) throws Exception {
        try {
            this.build(params, gastosIngresos);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(gastosIngresos);
    }

    @Override
    public GastosIngresos build(Map<String, Object> params, GastosIngresos gastosIngresos){
        try {
            Double gastoMensual = JsonUtils.obtDouble(params, "gastoMensual");
            if(gastoMensual == null) throw new IllegalArgumentException("El campo gastoMensual es obligatorio");
            gastosIngresos.setGastoMensual(gastoMensual);

            String dependeEconomicamente = JsonUtils.obtString(params, "dependeEconomicamente");
            if(dependeEconomicamente == null) throw new IllegalArgumentException("El campo dependeEconomicamente es obligatorio");
            gastosIngresos.setDependeEconomicamente(dependeEconomicamente);

            String nombreQuienDependes = JsonUtils.obtString(params, "nombreQuienDependes");
            if(nombreQuienDependes == null) throw new IllegalArgumentException("El campo nombreQuienDependes es obligatorio");
            gastosIngresos.setNombreQuienDependes(nombreQuienDependes);

            // Double gastoMensual = JsonUtils.obtDouble(params, "gastoMensual");
            // if(gastoMensual == null) throw new IllegalArgumentException("El campo gastoMensual es obligatorio");
            // gastosIngresos.setGastoMensual(gastoMensual);

            Boolean solicitaBecaAlimenticia = JsonUtils.obtBoolean(params, "solicitaBecaAlimenticia");
            if(solicitaBecaAlimenticia == null) throw new IllegalArgumentException("El campo solicitaBecaAlimenticia es obligatorio");
            gastosIngresos.setSolicitaBecaAlimenticia(solicitaBecaAlimenticia);

            Boolean trabajoTipo = JsonUtils.obtBoolean(params, "trabajoTemporal");
            if(trabajoTipo == null) throw new IllegalArgumentException("El campo trabajoTipo es obligatorio");
            gastosIngresos.setTrabajoTipo(trabajoTipo);

            String ocupacion = JsonUtils.obtString(params, "ocupacion");
            if(ocupacion == null) throw new IllegalArgumentException("El campo ocupacion es obligatorio");
            gastosIngresos.setOcupacion(ocupacion);

            String otro = JsonUtils.obtString(params, "otro");
            if(otro == null) throw new IllegalArgumentException("El campo otro es obligatorio");
            gastosIngresos.setOtro(otro);


        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return gastosIngresos;
    }

    //ESTE METODO SE OCUPA CUANDO YA TENEMOS LA INSTANCIA QUE QUEREMOS ACTUALIZAR
    @Override
    public GastosIngresos updateInstance(GastosIngresos gastosIngresosInstance) throws Exception {
        GastosIngresos gastosIngresosBD = this.findById(gastosIngresosInstance.getIdGatosIngresos());
        
        // gastosIngresosBD.setCuantoHacienden(gastosIngresosInstance.getCuantoHacienden());
        gastosIngresosBD.setDependeEconomicamente(gastosIngresosInstance.getDependeEconomicamente());
        gastosIngresosBD.setNombreQuienDependes(gastosIngresosInstance.getNombreQuienDependes());
        gastosIngresosBD.setGastoMensual(gastosIngresosInstance.getGastoMensual());
        gastosIngresosBD.setSolicitaBecaAlimenticia(gastosIngresosInstance.getSolicitaBecaAlimenticia());
        gastosIngresosBD.setTrabajoTipo(gastosIngresosInstance.getTrabajoTipo());
        gastosIngresosBD.setOcupacion(gastosIngresosInstance.getOcupacion());
        gastosIngresosBD.setOtro(gastosIngresosInstance.getOtro());
        
        return this.save(gastosIngresosBD);
    }

    @Override
    public void deleteById(Long id) {
        GastosIngresos gastosIngresos = this.findById(id);
        if (gastosIngresos!= null){
            gastosIngresosRepository.deleteById(id);
        }
    }
}
