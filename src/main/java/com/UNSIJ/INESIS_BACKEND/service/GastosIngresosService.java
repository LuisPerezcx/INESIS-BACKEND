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
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    AlumnoServiceJPA alumnoService;

    @Autowired
    FechasRegistradasServiceJPA fechasRegistradasServiceJPA;

    @Override
    public List<GastosIngresosFamiliares> findAll() {
        return gastosIngresosFamiliaresRepository.findAll();
    }

    @Override
    public GastosIngresosFamiliares findById(Long id) {
        return gastosIngresosFamiliaresRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("GastosIngresosFamiliares no encontrado con ID: " + id));
    }

    @Override
    public GastosIngresosFamiliares save(GastosIngresosFamiliares GastosIngresosFamiliares) throws Exception {
        Alumno alumno = GastosIngresosFamiliares.getAlumno();
        if(!fechasRegistradasServiceJPA.permitirRegistro(alumno.getCarrera().getId()))
            throw new IllegalArgumentException("No es posible registrar tus datos en este momento. " +
                    "El periodo de registro para tu carrera no está activo actualmente.");
        return gastosIngresosFamiliaresRepository.save(GastosIngresosFamiliares);
    }

    @Override
    @Transactional
    public GastosIngresosFamiliares create(Map<String, Object> params) throws Exception {
        GastosIngresosFamiliares ejemplo = new GastosIngresosFamiliares();
        try {
            Long idAlumno = JsonUtils.obtLong(params, "alumnoId");
            if (idAlumno == null) throw new IllegalArgumentException("El campo idAlumno es obligatorio");
            Alumno alumno = alumnoService.findById(idAlumno);
            ejemplo.setAlumno(alumno);
            this.build(params, ejemplo);
            ejemplo.setModuloCompleto(true);
            ejemplo = this.save(ejemplo);
            alumno.setGastosIngresosFamiliares(ejemplo);
            alumnoService.save(alumno);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // esto es opcional sirve para depuracion si ocurre algun error inesperado
            throw new IllegalArgumentException("Error al construir el ejemplo");
        }
        return ejemplo;
    }

    @Override
    public GastosIngresosFamiliares update(GastosIngresosFamiliares gIngresosFamiliares,
                                           Map<String, Object> params) throws Exception {
        try {
            // Reconstruir el objeto con los datos nuevos
            this.build(params, gIngresosFamiliares);
            gIngresosFamiliares.setModuloCompleto(true);

            // Guardar y retornar
            return this.save(gIngresosFamiliares);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al actualizar GastosIngresosFamiliares");
        }
    }

    @Override
    @Transactional
    public GastosIngresosFamiliares build(Map<String, Object> params, GastosIngresosFamiliares gIngresosFamiliares) {
        try {
            // VALIDACIONES Y CAMPOS BÁSICOS
            Integer personasAportan = JsonUtils.obtInteger(params, "personasAportan");
            Integer ingresoTotal = JsonUtils.obtInteger(params, "ingresoTotal");
            Integer personasDependen = JsonUtils.obtInteger(params, "personasDependen");
            Double ingresoBrutoTotal = JsonUtils.obtDouble(params, "ingresoBrutoTotal");

            if (personasAportan == null || ingresoTotal == null || personasDependen == null || ingresoBrutoTotal == null) {
                throw new IllegalArgumentException("Campos obligatorios faltantes");
            }

            gIngresosFamiliares.setNummeroPersonasAportan(personasAportan);
            gIngresosFamiliares.setIngresoTotal(ingresoTotal);
            gIngresosFamiliares.setNumeroPersonasDependen(personasDependen);
            gIngresosFamiliares.setIngresoBrutoTotal(ingresoBrutoTotal);


            // GASTOS FAMILIARES
            Map<String, Object> gastos = (Map<String, Object>) params.get("gastos");
            if (gIngresosFamiliares.getGastoFamiliarModel() != null) {
                gastoFamiliarServiceJPA.update(gIngresosFamiliares.getGastoFamiliarModel(), gastos);
            } else {
                gIngresosFamiliares.setGastoFamiliarModel(gastoFamiliarServiceJPA.create(gastos));
            }

            // RECIBO DE LUZ
            Map<String, Object> reciboLuz = (Map<String, Object>) params.get("reciboLuz");
            if (gIngresosFamiliares.getReciboLuzModel() != null) {
                reciboLuzFamiliaJPA.update(gIngresosFamiliares.getReciboLuzModel(), reciboLuz);
            } else {
                gIngresosFamiliares.setReciboLuzModel(reciboLuzFamiliaJPA.create(reciboLuz));
            }

            gIngresosFamiliares = gastosIngresosFamiliaresRepository.save(gIngresosFamiliares);

            // INGRESOS FAMILIARES
            List<Map<String, Object>> personas = (List<Map<String, Object>>) params.get("personas");
            if (personas != null) {
                if (gIngresosFamiliares.getIngresosFamiliar() != null) {
                    gIngresosFamiliares.getIngresosFamiliar().clear(); // orphanRemoval elimina los anteriores
                } else {
                    gIngresosFamiliares.setIngresosFamiliar(new ArrayList<>());
                }

                for (Map<String, Object> personaData : personas) {
                    IngresoFamiliarModel ingreso = new IngresoFamiliarModel();
                    ingresoFamiliarJPA.build(personaData, ingreso);
                    ingreso.setGastosIngresosFamiliares(gIngresosFamiliares);
                    ingresoFamiliarJPA.save(ingreso);
                    gIngresosFamiliares.getIngresosFamiliar().add(ingreso);
                }
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al construir GastosIngresosFamiliares");
        }

        System.out.println("Cuerpo que se manda a la base: " + gIngresosFamiliares);
        return gIngresosFamiliares;
    }


    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}