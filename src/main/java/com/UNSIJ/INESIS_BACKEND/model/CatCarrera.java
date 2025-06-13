package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_carrera")
public class CatCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_carrera")
    private Long id;

    @Column(name = "nombre_carrera")
    private String nombreCarrera;

}
