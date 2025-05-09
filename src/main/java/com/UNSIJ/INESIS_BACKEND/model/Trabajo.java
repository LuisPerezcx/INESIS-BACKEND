package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "trabajo")
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabajo")
    private Long idTrabajo;

    @Column(name = "nombre_trabajo")
    private String nombreTrabajo;

    @Column(name = "telefono_trabajo")
    @Size(max = 10)
    private String telefonoTrabajo;

    @Column(name = "ingreso_mensual")
    private Double ingresoMensual;

    @Column(name = "domicilio")
    private String domicilioTrabajo;
}
