package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_situacion_vivienda")
public class CatSituacionVivienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_situacion_vivienda")
    private Long id;

    @Column(name = "nombre_situacion")
    private String nombreSituacion;

}
