package com.UNSIJ.INESIS_BACKEND.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "gastos_ingresos_familiares")
public class GastosIngresosFamiliares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gastos_ingresos_familiares")
    private Long id;

    @Column(name = "modulo_completo")
    private Boolean moduloCompleto;

    @NotNull
    @Column(name = "num_personas_aportan")
    private int nummeroPersonasAportan;

    @NotNull
    @Column(name = "ingreso_total")
    private double ingresoTotal;

    @NotNull
    @Column(name = "ingreso_total_bruto")
    private double ingresoBrutoTotal;

    @NotNull
    @Column(name = "num_personas_dependen")
    private int numeroPersonasDependen;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_gasto_familiar", referencedColumnName = "id_gasto_familiar")
    GastoFamiliarModel gastoFamiliarModel;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_recibo_luz", referencedColumnName = "id_recibo_luz")
    ReciboLuz reciboLuzModel;

    @OneToMany(mappedBy = "gastosIngresosFamiliares", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngresoFamiliarModel> ingresosFamiliar = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    @JsonIgnore
    @ToString.Exclude
    private Alumno alumno;

}
