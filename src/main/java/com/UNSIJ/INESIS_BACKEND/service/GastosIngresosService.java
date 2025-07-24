package com.UNSIJ.INESIS_BACKEND.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.UNSIJ.INESIS_BACKEND.model.*;
import com.UNSIJ.INESIS_BACKEND.repository.*;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IGatosIngresoFamiliares;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class GastosIngresosService implements IGatosIngresoFamiliares {

    @Autowired
    GastosIngresosFamiliaresRepository gastosIngresosFamiliaresRepository;

    @Autowired
    GastoFamiliarServiceJPA gastoFamiliarServiceJPA;

    @Autowired
    ReciboLuzFamiliaJPA reciboLuzFamiliaJPA;

    @Autowired
    IngresoFamiliarJPA ingresoFamiliarJPA;

    @Override
    public List<GastosIngresosFamiliares> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public GastosIngresosFamiliares findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public GastosIngresosFamiliares save(GastosIngresosFamiliares GastosIngresosFamiliares) throws Exception {
        return gastosIngresosFamiliaresRepository.save(GastosIngresosFamiliares);
    }

    @Override
    public GastosIngresosFamiliares create(Map<String, Object> params) throws Exception {
        GastosIngresosFamiliares ejemplo = new GastosIngresosFamiliares();
        try {
            ejemplo.setCompleto(true);
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
    public GastosIngresosFamiliares update(GastosIngresosFamiliares GastosIngresosFamiliares,
            Map<String, Object> params) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public GastosIngresosFamiliares build(Map<String, Object> params, GastosIngresosFamiliares gIngresosFamiliares) {
        try {
            gIngresosFamiliares.setNummeroPersonasAportan(JsonUtils.obtInteger(params, "personasAportan"));
            gIngresosFamiliares.setIngresoTotal(JsonUtils.obtDouble(params, "ingresoTotal"));
            gIngresosFamiliares.setIngresoBrutoTotal(JsonUtils.obtDouble(params, "ingresoBrutoTotal"));
            gIngresosFamiliares.setNumeroPersonasDependen(JsonUtils.obtInteger(params, "personasDependen"));

            Map<String, Object> gastos = (Map<String, Object>) params.get("gastos");
            GastoFamiliarModel gastoFamiliar = gastoFamiliarServiceJPA.create(gastos);
            gIngresosFamiliares.setGastoFamiliarModel(gastoFamiliar);

            Map<String, Object> reciboLuz = (Map<String, Object>) params.get("reciboLuz");
            ReciboLuz reciboLuzM = reciboLuzFamiliaJPA.create(reciboLuz);
            gIngresosFamiliares.setReciboLuzModel(reciboLuzM);

            gIngresosFamiliares = gastosIngresosFamiliaresRepository.save(gIngresosFamiliares);

            ArrayList<IngresoFamiliarModel> nIngresoFamiliarModels = new ArrayList<>();
            List<Map<String, Object>> personas = (List<Map<String, Object>>) params.get("personas");
            for (Map<String, Object> personaData : personas) {
                IngresoFamiliarModel ingreso = new IngresoFamiliarModel();
                ingresoFamiliarJPA.build(personaData, ingreso);
                ingreso.setGastosIngresosFamiliares(gIngresosFamiliares);
                ingresoFamiliarJPA.save(ingreso);
                nIngresoFamiliarModels.add(ingreso);
            }
            gIngresosFamiliares.setIngresosFamiliares(nIngresoFamiliarModels);

            
            if(gIngresosFamiliares.getIngresosFamiliares() == null  )
            throw new IllegalArgumentException("Gastos ingresoa familiares no puede ser nulo");

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }

        System.out.println("Cuerpo que se manda a la base"+gIngresosFamiliares);
        return gIngresosFamiliares;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}