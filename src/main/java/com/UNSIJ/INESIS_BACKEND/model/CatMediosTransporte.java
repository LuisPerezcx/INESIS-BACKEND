package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_medios_transporte")
public class CatMediosTransporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_medios_transporte")
    private Long id;

    @NotNull
    @Column(name = "nombre_medio")
    private String nombreMedio;
}
