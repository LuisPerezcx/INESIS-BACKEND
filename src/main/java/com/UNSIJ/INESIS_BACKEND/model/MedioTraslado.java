package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medios_traslado")
public class MedioTraslado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medios_traslado")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mis_datos", nullable = false)
    private MisDatos misDatos;

    @ManyToOne
    @JoinColumn(name = "id_cat_medios_transporte", nullable = false)
    private CatMediosTransporte catMediosTransporte;
}
