package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "ingreso_familiar")
public class IngresoFamiliarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso_familiar")
    private Long id;

    @NotNull
    @Column(name = "nombre_persona")
    private String nombrePersona;

    @NotNull
    @Column(name = "ingreso_bruto")
    private Double ingresoBruto;

    @NotNull
    @Column(name = "ingreso_neto")
    private Double ingresoNeto;

    @NotNull
    @Column(name = "lugar_trabajo")
    private String lugarTrabajo;

    @NotNull
    @Column(name = "puestoTrabajo")
    private String puestoTrabajo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cat_parentesco", referencedColumnName = "id_cat_parentesco")
    CatParentesco parentesco;


    /* 
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cat_ocupacion", referencedColumnName = "id_cat_ocupacion")
    OcupacionModel ocupacionModel;
    */

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_gastos_ingresos_familiares", referencedColumnName = "id_gastos_ingresos_familiares")
    GastosIngresosFamiliares gastosIngresosFamiliares;
}
