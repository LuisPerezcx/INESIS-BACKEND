package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "gasto_familiar")
public class GastoFamiliarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gasto_familiar")
    private Long id;

    @NotNull
    @Column(name = "gasto_alimentacion")
    private Double gastoAlimentacion;

    @NotNull
    @Column(name = "gasto_renta")
    private Double gastoRenta;

    @NotNull
    @Column(name = "gasto_servicios")
    private Double gastoServicios;

    @NotNull
    @Column(name = "gasto_escolares")
    private Double gastoEscolares;

    @NotNull
    @Column(name = "gasto_ropa")
    private Double gastoRopa;

    @NotNull
    @Column(name = "gasto_transporte")
    private Double gastoTransporte;

    @NotNull
    @Column(name = "gasto_otros")
    private Double gastoOtros;

    @NotNull
    @Column(name = "total_gastos")
    private Double totalGastos;
}
