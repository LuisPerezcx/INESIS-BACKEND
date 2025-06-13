package com.UNSIJ.INESIS_BACKEND.DTO;

import lombok.Data;

@Data
public class ReciboLuzDTO {
    private String titular;
    private String periodoInicio;
    private String periodoFin;

    private String nombreArchivo;
    private String nombreOriginal;
    private String rutaRecibo;

    private String ultimoPago;
    private String promedioPago;

    private String contenidoBase64;

    private String observaciones;
}
