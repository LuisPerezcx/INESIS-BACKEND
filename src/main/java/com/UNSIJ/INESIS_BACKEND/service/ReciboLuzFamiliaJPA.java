package com.UNSIJ.INESIS_BACKEND.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.UNSIJ.INESIS_BACKEND.model.ReciboLuz;
import com.UNSIJ.INESIS_BACKEND.repository.ReciboLuzRepository;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IReciboLuz;
import com.UNSIJ.INESIS_BACKEND.utils.ArchivoUtil;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class ReciboLuzFamiliaJPA implements IReciboLuz {

    @Autowired
    ReciboLuzRepository reciboLuzRepository;

    @Value("${archivos.recibo.luz.carpeta}")
    private String rutaBase;

    @Override
    public List<ReciboLuz> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public ReciboLuz findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public ReciboLuz save(ReciboLuz ReciboLuzModel) throws Exception {
        return reciboLuzRepository.save(ReciboLuzModel);
    }

    @Override
    public ReciboLuz create(Map<String, Object> params) throws Exception {
        ReciboLuz ejemplo = new ReciboLuz();
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
    public ReciboLuz update(ReciboLuz ReciboLuzModel, Map<String, Object> params) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ReciboLuz build(Map<String, Object> params, ReciboLuz ReciboLuzModel){
           try {
            String titular = JsonUtils.obtString(params,"titular");
            String periodoInicio = JsonUtils.obtString(params,"periodoInicio");
            String periodoFin = JsonUtils.obtString(params,"periodoFin");
            String nombreArchivo = JsonUtils.obtString(params,"nombreArchivo");
            String nombreOriginal = JsonUtils.obtString(params,"nombreOriginal");
            String domicilio = JsonUtils.obtString(params, "domicilio");


            Double ultimoPago = JsonUtils.obtDouble(params,"ultimoPago");
            Double promedioPago = JsonUtils.obtDouble(params,"promedioPago");
            String observaciones = JsonUtils.obtString(params,"observaciones");


            String contenidoBase64 = JsonUtils.obtString(params, "contenidoBase64"); // Asegúrate que llegue este campo


            //VERIFICACION DEL CAMPO NUMERO
            if(titular == null) throw new IllegalArgumentException("El campo titular es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONTif(periodoInicio == null) throw new IllegalArgumentException("El campo periodoInicio es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(periodoFin == null) throw new IllegalArgumentException("El campo periodoFin es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(nombreArchivo == null) throw new IllegalArgumentException("El campo nombreArchivo es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(nombreOriginal == null) throw new IllegalArgumentException("El campo nombreOriginal es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(ultimoPago == null) throw new IllegalArgumentException("El campo ultimoPago es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(promedioPago == null) throw new IllegalArgumentException("El campo promedioPago es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
            if(observaciones == null) throw new IllegalArgumentException("El campo observaciones es obligatorio"); //ESTOS MENSAJES SE MOSTRARÁN EN EL FRONT
           if(contenidoBase64 != null && !contenidoBase64.isEmpty()){
               if(nombreArchivo == null) throw new IllegalArgumentException("El campo nombreArchivo es obligatorio");
               if(nombreOriginal == null) throw new IllegalArgumentException("El campo nombreOriginal es obligatorio");

               String rutaRecibo = ArchivoUtil.guardarArchivoBase64(contenidoBase64, nombreArchivo, rutaBase);

               ReciboLuzModel.setNombreArchivo(nombreArchivo);
               ReciboLuzModel.setNombreOriginal(nombreOriginal);
               ReciboLuzModel.setRutaRecibo(rutaRecibo);
           }
            String rutaRecibo = ArchivoUtil.guardarArchivoBase64(contenidoBase64, nombreArchivo, rutaBase);

           
            ReciboLuzModel.setTitular(titular);
            ReciboLuzModel.setPeriodoInicio(periodoInicio);
            ReciboLuzModel.setPeriodoFin(periodoFin);
            ReciboLuzModel.setNombreArchivo(nombreArchivo);
            ReciboLuzModel.setNombreOriginal(nombreOriginal);
            ReciboLuzModel.setRutaRecibo(rutaRecibo);
            ReciboLuzModel.setUltimoPago(ultimoPago);
            ReciboLuzModel.setPromedioPago(promedioPago);
            ReciboLuzModel.setObservaciones(observaciones);
            ReciboLuzModel.setDomicilio(domicilio);


        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve  para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return ReciboLuzModel;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
