package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UNSIJ.INESIS_BACKEND.model.GastoFamiliarModel;
import com.UNSIJ.INESIS_BACKEND.model.GastosIngresosFamiliares;
import com.UNSIJ.INESIS_BACKEND.repository.GastoFamiliarRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IGastoFamiliar;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class GastoFamiliarServiceJPA implements IGastoFamiliar {

    @Autowired
    GastoFamiliarRepository gastoFamiliarRepository;

    @Override
    public List<GastoFamiliarModel> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public GastoFamiliarModel findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public GastoFamiliarModel save(GastoFamiliarModel GastoFamiliarModel) throws Exception {
        return gastoFamiliarRepository.save(GastoFamiliarModel);
    }

    @Override
    public GastoFamiliarModel create(Map<String, Object> params) throws Exception {
         GastoFamiliarModel ejemplo = new GastoFamiliarModel();
        try {
            this.build(params, ejemplo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return this.save(ejemplo);
    }

    @Override
    public GastoFamiliarModel update(GastoFamiliarModel GastoFamiliarModel, Map<String, Object> params)
            throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public GastoFamiliarModel build(Map<String, Object> params, GastoFamiliarModel GastoFamiliarModel){
        try {
            Double gastoAlimentacion = JsonUtils.obtDouble(params,"gastoAlimentacion");
            Double gastoRenta = JsonUtils.obtDouble(params,"gastoRenta");
            Double gastoServicios = JsonUtils.obtDouble(params,"gastoServicios");
            Double gastoEscolares = JsonUtils.obtDouble(params,"gastoEscolares");
            Double gastoRopa = JsonUtils.obtDouble(params,"gastoRopa");
            Double gastoTransporte = JsonUtils.obtDouble(params,"gastoTransporte");
            Double gastoOtros = JsonUtils.obtDouble(params,"gastoOtros");
            Double totalGastos = JsonUtils.obtDouble(params,"totalGastos");

            //VERIFICACION DEL CAMPO NUMERO
            if(gastoAlimentacion == null) throw new IllegalArgumentException("El campo gastoAlimentacion es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(gastoRenta == null) throw new IllegalArgumentException("El campo gastoRenta es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(gastoServicios == null) throw new IllegalArgumentException("El campo gastoServicios es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(gastoEscolares == null) throw new IllegalArgumentException("El campo gastoEscolares es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(gastoRopa == null) throw new IllegalArgumentException("El campo gastoRopa es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(gastoTransporte == null) throw new IllegalArgumentException("El campo gastoTransporte es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(gastoOtros == null) throw new IllegalArgumentException("El campo gastoOtros es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(totalGastos == null) throw new IllegalArgumentException("El campo totalGastos es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
           
           
            GastoFamiliarModel.setGastoAlimentacion(gastoAlimentacion);
            GastoFamiliarModel.setGastoRenta(gastoRenta);
            GastoFamiliarModel.setGastoServicios(gastoServicios);
            GastoFamiliarModel.setGastoEscolares(gastoEscolares);
            GastoFamiliarModel.setGastoRopa(gastoRopa);
            GastoFamiliarModel.setGastoTransporte(gastoTransporte);
            GastoFamiliarModel.setGastoOtros(gastoOtros);
            GastoFamiliarModel.setTotalGastos(totalGastos);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return GastoFamiliarModel;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
