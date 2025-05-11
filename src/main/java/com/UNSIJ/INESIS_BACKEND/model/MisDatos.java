package com.UNSIJ.INESIS_BACKEND.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "mis_datos")
public class MisDatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mis_datos")
    private Long id;

    @NotNull
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @ManyToOne
    @JoinColumn(name = "id_cat_carrera", nullable = false)
    private CatCarreraModel carrera;

    @ManyToOne
    @JoinColumn(name = "id_cat_semestre", nullable = false)
    private CatSemestreModel semestre;

    @ManyToOne
    @JoinColumn(name = "id_cat_sexo")
    private CatSexoModel sexo;

    @ManyToOne
    @JoinColumn(name = "id_cat_estado_civil")
    private CatEstadoCivil estadoCivil;

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

    @OneToMany(mappedBy = "misDatos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transporte> transporte;

    @OneToMany(mappedBy = "misDatos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedioTraslado> mediosTraslado;
}