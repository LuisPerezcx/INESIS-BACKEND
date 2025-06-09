package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name = "gastos_ingresos_familiares")
public class GastosIngresosFamiliares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gastos_ingresos_familiares")
    private Long id;

    @NotNull
    @Column(name = "completo")
    private Boolean completo;

    @NotNull
    @Column(name = "num_personas_aportan")
    private int nummeroPersonasAportan;

    @NotNull
    @Column(name = "ingreso_total")
    private double ingresoTotal;

    @NotNull
    @Column(name = "num_personas_dependen")
    private int numeroPersonasDependen;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_ingreso_familiar", referencedColumnName = "id_ingreso_familiar")
    IngresoFamiliarModel ingresoFamiliarModel;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_gasto_familiar", referencedColumnName = "id_gasto_familiar")
    GastoFamiliarModel gastoFamiliarModel;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_recibo_luz", referencedColumnName = "id_recibo_luz")
    ReciboLuzModel reciboLuzModel;
}
