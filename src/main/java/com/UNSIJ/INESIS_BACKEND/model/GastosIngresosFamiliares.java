package com.UNSIJ.INESIS_BACKEND.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
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
    @JsonIgnore // Evita que Jackson serialice esta propiedad
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


    @OneToMany(mappedBy = "gastosIngresosFamiliares")
    @JsonManagedReference
    private List<IngresoFamiliarModel> ingresosFamiliares;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_gasto_familiar", referencedColumnName = "id_gasto_familiar")
    GastoFamiliarModel gastoFamiliarModel;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_recibo_luz", referencedColumnName = "id_recibo_luz")
    ReciboLuz reciboLuzModel;
}
