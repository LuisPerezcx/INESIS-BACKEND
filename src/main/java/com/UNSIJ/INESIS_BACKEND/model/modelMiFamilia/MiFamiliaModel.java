package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "MiFamilia")
public class MiFamiliaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "id_domicilio")
    private Integer idDomicilio;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "escolaridadPadre")
    private Integer escolaridadPadre;

    @Column(name = "escolaridadMadre")
    private Integer escolaridadMadre;

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
    private ViviendaFamiliarModel viviendaFamiliar;

    @ManyToOne
    @JoinColumn(name = "id_medios_estudio")
    private MediosEstudioModel mediosEstudio;
}
