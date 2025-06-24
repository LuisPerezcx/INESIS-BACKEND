package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.UNSIJ.INESIS_BACKEND.model.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "mi_familia")
public class MiFamilia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mi_familia")
    private Long id;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "tiene_internet", nullable = false)
    private boolean tieneInternet;

    @Column(name = "num_hermanos")
    private Integer numHermanos;

    @Column(name = "num_hermanos_estudiando")
    private Integer numHermanosEstudiando;

    @Column(name = "num_hermanos_no_estudiando")
    private Integer numHermanosNoEstudiando;

    @Column(name = "num_hermanos_licenciatura")
    private Integer numHermanosLicenciatura;

    @Column(name = "num_personas_dependen")
    private Integer numPersonasDependen;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_domicilio")
    private Domicilio domicilio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vivienda_familiar", referencedColumnName = "id_vivienda_familiar")
    private ViviendaFamiliar viviendaFamiliar;

    @OneToMany(mappedBy = "miFamilia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediosEstudio> mediosEstudio = new ArrayList<>();

    @OneToMany(mappedBy = "miFamilia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BienesHogar> bienesHogar = new ArrayList<>();

    @OneToMany(mappedBy = "miFamilia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonasDependientes> personasDependientes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_cat_escolaridad_padre")
    private CatEscolaridad escolaridadPadre;

    @ManyToOne
    @JoinColumn(name = "id_cat_escolaridad_madre")
    private CatEscolaridad escolaridadMadre;

    @Column(name = "modulo_completo")
    private Boolean moduloCompleto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    @JsonIgnore
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_cat_internet", referencedColumnName = "id_cat_internet")
    private CatInternet catInternet;

}
