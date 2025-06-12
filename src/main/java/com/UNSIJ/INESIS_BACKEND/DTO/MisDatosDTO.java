package com.UNSIJ.INESIS_BACKEND.DTO;

import lombok.Data;

import java.util.List;

@Data
public class MisDatosDTO {
    private Long id;
    private String nombreCompleto;
    private String idioma;
    private Boolean recursosSuficientes;
    private Boolean familiarComunero;
    private Boolean utilizaCelular;
    private Boolean tieneComputadora;

    private String carrera;
    private String semestre;
    private String sexo;
    private String estadoCivil;

    private List<String> mediosTraslado; // Solo los nombres

}
