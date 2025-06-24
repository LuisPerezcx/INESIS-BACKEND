package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "servicios_vivienda")
public class ServiciosVivienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicios_vivienda")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vivienda_familiar", nullable = false)
    @JsonIgnore
    private ViviendaFamiliar viviendaFamiliar;

    @ManyToOne
    @JoinColumn(name = "id_cat_servicio_vivienda", nullable = false)
    private CatServiciosVivienda catServiciosVivienda;
}
