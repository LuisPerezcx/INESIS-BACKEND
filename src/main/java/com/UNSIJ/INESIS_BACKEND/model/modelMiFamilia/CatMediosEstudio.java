package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_medios_estudio")
public class CatMediosEstudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_medios_estudio")
    private Long id;

    @Column(name = "nombre_medios")
    private String nombreMedios;
}
