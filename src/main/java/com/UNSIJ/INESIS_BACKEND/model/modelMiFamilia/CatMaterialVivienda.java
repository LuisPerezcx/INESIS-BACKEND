package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_material_vivienda")

public class CatMaterialVivienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_material_vivienda")
    private Long id;

    @Column(name = "nombre_material")
    private String nombreMaterial;
}
