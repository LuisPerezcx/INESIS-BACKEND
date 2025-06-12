package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UNSIJ.INESIS_BACKEND.model.*;
import com.UNSIJ.INESIS_BACKEND.repository.*;

@Service
public class GastosIngresosService {

    @Autowired private GastoFamiliarRepository gastoRepo;
    @Autowired private IngresoFamiliarRepository ingresoRepo;
    @Autowired private ReciboLuzRepository reciboRepo;
    @Autowired private GastosIngresosFamiliaresRepository familiaRepo;
    @Autowired private ParentescoRepository parentescoRepo;
    @Autowired private OcupacionRepository ocupacionRepo;

    public List<GastosIngresosFamiliares> findAll() {
        return familiaRepo.findAll();
    }

    public GastosIngresosFamiliares findById(Long id) {
        return familiaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("No se encontró el registro con id: " + id));
    }

    public GastosIngresosFamiliares create(Map<String, Object> params) {
        try {
            System.out.println("=== Recibiendo params ===");
            System.out.println(params);
    
            // === 1. GUARDAR GASTOS ===
            Map<String, Object> gastoData = (Map<String, Object>) params.get("gastos");
            System.out.println("GastoData recibido: " + gastoData);
    
            GastoFamiliarModel gasto = new GastoFamiliarModel();
            gasto.setGastoAlimentacion(getDoubleFromObject(gastoData.get("Alimentación")));
            gasto.setGastoRenta(getDoubleFromObject(gastoData.get("Renta")));
            gasto.setGastoServicios(getDoubleFromObject(gastoData.get("Servicios")));
            gasto.setGastoEscolares(getDoubleFromObject(gastoData.get("Gastos escolares")));
            gasto.setGastoRopa(getDoubleFromObject(gastoData.get("Ropa")));
            gasto.setGastoTransporte(getDoubleFromObject(gastoData.get("Transporte")));
            gasto.setGastoOtros(getDoubleFromObject(gastoData.get("Otros")));
            gasto.setTotalGastos(getDoubleFromObject(gastoData.get("total")));
            gasto = gastoRepo.save(gasto);
            System.out.println("Gasto guardado con ID: " + gasto.getId());
    
            // === 2. GUARDAR RECIBO DE LUZ ===
            Map<String, Object> reciboData = (Map<String, Object>) params.get("reciboLuz");
            System.out.println("ReciboLuzData recibido: " + reciboData);
    
            ReciboLuz recibo = new ReciboLuz();
            recibo.setTitular((String) reciboData.get("titular"));
            recibo.setPeriodoInicio((String) reciboData.getOrDefault("periodoInicio", null));
            recibo.setPeriodoFin((String) reciboData.getOrDefault("periodoFin", null));
            recibo.setPagoBimestral((String) reciboData.getOrDefault("pagoBimestral", null));
            recibo.setPagoPromedioMes((String) reciboData.getOrDefault("promedioPago", null));
            recibo.setContenidoBase64((String) reciboData.get("contenidoBase64"));
            recibo.setNombreArchivo((String) reciboData.getOrDefault("nombreArchivo", null));
            recibo = reciboRepo.save(recibo);
            System.out.println("Recibo guardado con ID: " + recibo.getId());
    
            // === 3. GUARDAR GASTOS_INGRESOS_FAMILIARES ===
            GastosIngresosFamiliares familia = new GastosIngresosFamiliares();
            System.out.println("IngresoTotal recibido: " + params.get("ingresoTotal"));
            System.out.println("PersonasDependen recibido: " + params.get("personasDependen"));
            familia.setIngresoTotal(params.get("ingresoTotal").toString());
            familia.setNumeroPersonasDependen(params.get("personasDependen").toString());
            familia.setGastoFamiliarModel(gasto);
            familia.setReciboLuzModel(recibo);
            familia = familiaRepo.save(familia);
            System.out.println("Familia guardada con ID: " + familia.getId());
    
            // === 4. GUARDAR PERSONAS QUE APORTAN ===
            List<Map<String, Object>> personas = (List<Map<String, Object>>) params.get("personas");
            System.out.println("Personas recibidas: " + personas.size());
    
            for (Map<String, Object> persona : personas) {
                System.out.println("Procesando persona: " + persona);
    
                IngresoFamiliarModel ingreso = new IngresoFamiliarModel();
                ingreso.setNombrePersona((String) persona.get("name"));
                ingreso.setIngresoBruto(String.valueOf(persona.get("gross")));
                ingreso.setIngresoNeto(String.valueOf(persona.get("net")));
                ingreso.setLugarTrabajo((String) persona.get("company"));
                ingreso.setPuestoTrabajo((String) persona.get("job"));
    
                if (persona.containsKey("idParentesco")) {
                    Long idParentesco = ((Number) persona.get("idParentesco")).longValue();
                    System.out.println("Buscando Parentesco con ID: " + idParentesco);
                    ingreso.setParentesco(parentescoRepo.findById(idParentesco)
                        .orElseThrow(() -> new IllegalArgumentException("Parentesco no encontrado")));
                } else {
                    System.out.println("ERROR: Persona sin idParentesco");
                    throw new IllegalArgumentException("idParentesco es requerido para cada persona");
                }
    
                // Como quieres quitar el ocupacionModel, no hacemos nada aquí con él.
    
                ingreso.setGastosIngresosFamiliares(familia);
                ingresoRepo.save(ingreso);
                System.out.println("Ingreso guardado para: " + ingreso.getNombrePersona());
            }
    
            return familia;
    
        } catch (Exception e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
            throw new RuntimeException("Error al guardar los datos: " + e.getMessage(), e);
        }
    }
    

// Método auxiliar para convertir Object a Double sin lanzar NullPointerException
private Double getDoubleFromObject(Object obj) {
    if (obj == null) return 0.0;
    if (obj instanceof Number) return ((Number) obj).doubleValue();
    try {
        return Double.parseDouble(obj.toString());
    } catch (NumberFormatException e) {
        return 0.0;
    }
}

    

    public GastosIngresosFamiliares update(GastosIngresosFamiliares existente, Map<String, Object> params) {
        throw new UnsupportedOperationException("Actualizar aún no implementado");
    }

    public void deleteById(Long id) {
        familiaRepo.deleteById(id);
    }
}

