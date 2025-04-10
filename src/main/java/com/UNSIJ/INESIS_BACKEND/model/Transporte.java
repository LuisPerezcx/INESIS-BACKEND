package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transporte")
public class Transporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransporte;

    private Boolean llevaVehiculo;
    private String marca;
    private String modelo;
    private Integer anio;

    @ManyToOne
    @JoinColumn(name = "id_cat_tipo_transporte")
    private CatTipoTransporte catTipoTransporte;
}
