package com.UNSIJ.INESIS_BACKEND.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class DatosFamiliaresDTO {
    @NotNull
    private String completo;
    @NotNull
    private String numeroPersonasAportan;
    @NotNull
    private String ingresoTotal;
    @NotNull
    private String numeroPersonasDependen;

    @NotNull
    private IngresoFamiliarDTO ingresoFamiliar;

    @NotNull
    private GastoFamiliarDTO gastoFamiliar;

    @NotNull
    private ReciboLuzDTO reciboLuz;

    @Data
    public static class IngresoFamiliarDTO {
        @NotNull
        private String nombrePersona;
        @NotNull
        private String ingresoBruto;
        @NotNull
        private String ingresoNeto;
        @NotNull
        private String lugarTrabajo;
        @NotNull
        private String puestoTrabajo;
        @NotNull
        private Long parentescoId;
    }

    @Data
    public static class GastoFamiliarDTO {
        @NotNull
        private Long id; // suposición para identificar gasto familiar ya existente
    }

    @Data
    public static class ReciboLuzDTO {
        @NotNull
        private String titular;
        @NotNull
        private String periodoInicio;
        @NotNull
        private String periodoFin;
        @NotNull
        private String nombreArchivo;
        @NotNull
        private String nombreOriginal;
        @NotNull
        private String rutaRecibo;
        @NotNull
        private String pagoBimestral;
        @NotNull
        private String pagoPromedioMes;
        @NotNull
        private String contenidoBase64;
    }
}
