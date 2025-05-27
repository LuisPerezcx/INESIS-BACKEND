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
@Table(name = "servicios_vivienda")
public class ServiciosViviendaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicios_vivienda")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vivienda_familiar")
    private ViviendaFamiliarModel viviendaFamiliar;
}
