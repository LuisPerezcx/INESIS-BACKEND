package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "recibo_luz")
public class ReciboLuz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recibo_luz")
    private Long id;

    @NotNull
    @Column(name = "titular")
    private String titular;

    @NotNull
    @Column(name = "periodo_inicio")
    private String periodoInicio;

    @NotNull
    @Column(name = "periodo_fin")
    private String periodoFin;

    @NotNull
    @Column(name = "nombre_archivo")
    private String nombreArchivo; 

    @NotNull
    @Column(name = "nombre_original")
    private String nombreOriginal;

    @NotNull
    @Column(name = "ruta_recibo")
    private String rutaRecibo;

    @NotNull
    @Column(name = "pago_bimestral")
    private Double ultimoPago; // Puedes usar BigDecimal si vas a operar con montos

    @NotNull
    @Column(name = "domicilio")
    private String domicilio;

    @NotNull
    @Column(name = "pago_promedio_mes")
    private Double promedioPago;

    @Column(name = "observaciones")
    private String observaciones;
}
