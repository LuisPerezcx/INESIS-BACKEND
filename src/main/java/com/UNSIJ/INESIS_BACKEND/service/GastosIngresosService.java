package com.UNSIJ.INESIS_BACKEND.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.UNSIJ.INESIS_BACKEND.dto.GastoDTO;
import com.UNSIJ.INESIS_BACKEND.dto.PersonaDTO;
import com.UNSIJ.INESIS_BACKEND.dto.ReciboLuzDTO;
import com.UNSIJ.INESIS_BACKEND.model.*;
import com.UNSIJ.INESIS_BACKEND.repository.*;
import com.UNSIJ.INESIS_BACKEND.service.interfaces.IGatosIngresoFamiliares;
import com.UNSIJ.INESIS_BACKEND.utils.ArchivoUtil;
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
            this.build(params, ejemplo);
            ejemplo.setCompleto(true);
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
            gIngresosFamiliares.setIngresoTotal(JsonUtils.obtInteger(params, "ingresoTotal"));
            gIngresosFamiliares.setNumeroPersonasDependen(JsonUtils.obtInteger(params, "personasDependen"));

            Map<String, Object> gastos = (Map<String, Object>) params.get("gastos");
            GastoFamiliarModel gastoFamiliar = gastoFamiliarServiceJPA.create(gastos);
            gIngresosFamiliares.setGastoFamiliarModel(gastoFamiliar);

            Map<String, Object> reciboLuz = (Map<String, Object>) params.get("reciboLuz");
            ReciboLuzModel reciboLuzM = reciboLuzFamiliaJPA.create(reciboLuz);
            gIngresosFamiliares.setReciboLuzModel(reciboLuzM);

            List<Map<String, Object>> personas = (List<Map<String, Object>>) params.get("personas");
            for (Map<String, Object> personaData : personas) {
                IngresoFamiliarModel ingreso = new IngresoFamiliarModel();
                ingresoFamiliarJPA.build(personaData, ingreso);
                ingreso.setGastosIngresosFamiliares(gIngresosFamiliares);
                ingresoFamiliarJPA.save(ingreso);
            }
 
            Map<String, Object> ingresoFamiliar = (Map<String, Object>) params.get("s");
            IngresoFamiliarModel ingresoFamiliarModel = ingresoFamiliarJPA.create(ingresoFamiliar);
            gIngresosFamiliares.setIngresoFamiliarModel(ingresoFamiliarModel);



        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }

        return gIngresosFamiliares;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}