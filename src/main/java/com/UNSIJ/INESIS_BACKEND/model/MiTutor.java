package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tutor")
public class MiTutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutor")
    private Long idTutor;

    @Column(name = "nombre_tutor")
    private String nombreTutor;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "trabajador_sunero")
    private Boolean trabajadorSuneo;

    @Column(name = "comparte_vivienda")
    private Boolean comparteVivienda;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cat_tipo_trabajo")
    private CatTipoTrabajo catTipoTrabajo;

    @Column(name = "ocupacion_otro")
    private String ocupacionOtro;

    @Column(name = "modulo_completo")
    private Boolean moduloCompleto;

    @ManyToOne
    @JoinColumn(name = "id_cat_parentesco")
    private CatParentesco parentesco;

    @ManyToOne
    @JoinColumn(name = "id_cat_ocupacion")
    private Ocupacion ocupacion;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_domicilio")
    private Domicilio domicilio;
}
