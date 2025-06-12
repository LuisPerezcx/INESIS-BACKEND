package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transporte")
public class Transporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transporte")
    private Long idTransporte;

    private String marca;

    private String modelo;

    private Integer anio;

    @ManyToOne
    @JoinColumn(name = "id_cat_tipo_transporte")
    private CatTipoTransporte catTipoTransporte;
}
