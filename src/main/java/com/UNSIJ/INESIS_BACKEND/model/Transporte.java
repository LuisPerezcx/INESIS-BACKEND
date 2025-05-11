package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "transporte")
public class Transporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransporte;

    @NotNull
    private String llevaVehiculo;

    @NotNull
    private String marca;

    @NotNull
    private String modelo;

    @NotNull
    private Integer anio;

    @ManyToOne
    @JoinColumn(name = "id_cat_tipo_transporte")
    private CatTipoTransporte catTipoTransporte;

    @ManyToOne
    @JoinColumn(name = "id_mis_datos", nullable = false)
    private MisDatos misDatos;

}
