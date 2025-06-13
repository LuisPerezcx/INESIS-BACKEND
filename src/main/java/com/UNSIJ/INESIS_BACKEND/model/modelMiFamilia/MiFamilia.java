package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import com.UNSIJ.INESIS_BACKEND.model.Alumno;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "MiFamilia")
public class MiFamilia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "id_domicilio")
    private Integer idDomicilio;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "num_hermanos")
    private Integer numHermanos;

    @Column(name = "num_hermanos_estudiando")
    private Integer numHermanosEstudiando;

    @Column(name = "num_hermanos_no_estudiando")
    private Integer numHermanosNoEstudiando;

    @Column(name = "num_hermanos_licenciatura")
    private Integer numHermanosLicenciatura;

    @ManyToOne
    @JoinColumn(name = "id_vivienda_familiar")
    private ViviendaFamiliar viviendaFamiliar;

    @ManyToOne
    @JoinColumn(name = "id_medios_estudio")
    private MediosEstudio mediosEstudio;

    @ManyToOne
    @JoinColumn(name = "id_cat_escolaridad_padre")
    private CatEscolaridad escolaridadPadre;

    @ManyToOne
    @JoinColumn(name = "id_cat_escolaridad_madre")
    private CatEscolaridad escolaridadMadre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    @JsonIgnore
    private Alumno alumno;

    @Column(name = "modulo_completo")
    private Boolean moduloCompleto;
}
