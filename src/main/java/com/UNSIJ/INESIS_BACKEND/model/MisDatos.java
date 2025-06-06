package com.UNSIJ.INESIS_BACKEND.model;

import java.util.ArrayList;
import java.util.List;

import com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia.CatSituacionViviendaModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "mis_datos")
public class MisDatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mis_datos")
    private Long id;

    @NotNull
    @Column(name = "seccion_completa")
    private boolean completo = false;

    @NotNull
    @Column(name = "recursos_suficientes")
    private Boolean recursosSuficientes;

    @NotNull
    @Column(name = "familiar_comunero")
    private Boolean familiarComunero;

    @NotNull
    @Column(name = "utiliza_celular")
    private Boolean utilizaCelular;

    @NotNull
    @Column(name = "tiene_computadora")
    private Boolean tieneComputadora;

    @NotNull
    @Column(name = "idioma")
    private String idioma;

    @NotNull
    @Column(name = "nombre_casa_huesped")
    private String nombreCasaHuesped;

    @NotNull
    @Column(name = "lleva_automovil")
    private Boolean llevaAutomovil;

    @NotNull
    @Column(name = "lleva_motocicleta")
    private Boolean llevamotocicleta;

    @ManyToOne
    @JoinColumn(name = "id_cat_estado_civil")
    private CatEstadoCivil estadoCivil;

    @ManyToOne
    @JoinColumn(name = "id_cat_sexo")
    private CatSexoModel sexo;

    @ManyToOne
    @JoinColumn(name = "id_cat_situacion_vivienda")
    private CatSituacionViviendaModel situacionVivienda;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_gastos_ingresos")
    @ToString.Exclude
    private GastosIngresos gastosIngresos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_transporte_automovil")
    private Transporte transporteAutomovil;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_transporte_motocicleta")
    private Transporte transporteMotocicleta;

    @OneToMany(mappedBy = "misDatos", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MedioTraslado> mediosTraslado = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_domicilio")
    private Domicilio domicilio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    @JsonIgnore
    private Alumno alumno;
}