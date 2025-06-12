package com.UNSIJ.INESIS_BACKEND.controller;

import com.UNSIJ.INESIS_BACKEND.model.GastosIngresosFamiliares;
import com.UNSIJ.INESIS_BACKEND.service.GastosIngresosService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gastosFamiliares")
public class GastosFamiliaresController {

    @Autowired
    private GastosIngresosService gastosService;

    // Recibe todo en JSON, no multipart/form-data
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GastosIngresosFamiliares> guardarMisDatos(@RequestBody Map<String, Object> payload) {


        try {
            System.out.println("Lo que recibe el back: " + payload);

            ObjectMapper mapper = new ObjectMapper();

            // Parsear las personas como lista de mapas o como objetos
            List<Map<String, Object>> personas = mapper.convertValue(payload.get("personas"), new TypeReference<>() {
            });
            Double ingresoTotal = Double.valueOf(payload.get("ingresoTota   l").toString());
            Integer personasDependen = Integer.valueOf(payload.get("personasDependen").toString());
            Map<String, Object> reciboLuz = mapper.convertValue(payload.get("reciboLuz"), new TypeReference<>() {
            });
            Map<String, Object> gastos = mapper.convertValue(payload.get("gastos"), new TypeReference<>() {
            });

            // Pasar datos al servicio en un Map o crear tu DTO/entidad aquí
            // Por ejemplo, crea un Map para enviar a servicio:
            Map<String, Object> params = Map.of(
                    "personas", personas,
                    "ingresoTotal", ingresoTotal,
                    "personasDependen", personasDependen,
                    "reciboLuz", reciboLuz,
                    "gastos", gastos
            );

            GastosIngresosFamiliares creado = gastosService.create(params);

            return ResponseEntity.ok(creado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
