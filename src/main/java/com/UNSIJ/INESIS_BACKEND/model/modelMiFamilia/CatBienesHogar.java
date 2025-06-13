package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_bienes_hogar")
public class CatBienesHogar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_bienes_hogar")
    private Long id;

    @Column(name = "nombre_bien")
    private String nombreBien;
}
