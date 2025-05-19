package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tutor")
public class MiTutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutor")
    private Long idTutor;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "trabajador_sunero")
    private Boolean trabajadorSuneo;

    @Column(name = "comparte_vivienda")
    private Boolean comparteVivienda;

    @Column(name = "tipo_trabajo")
    private String tipoTrabajo;

    @Column(name = "ocupacion_otro")
    private String ocupacionOtro;
}
