package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "fechas_registradas")
public class FechasRegistradasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fechas_registradas")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cat_carrera", referencedColumnName = "id_cat_carrera")
    private CatCarreraModel carrera;

    @NotNull
    @Column(name = "fecha_inicio")
    private Long fechaInicio;

    @NotNull
    @Column(name = "fecha_fin")
    private Long fechaFin;

    @NotNull
    @Column(name = "status")
    private String status; 

}
