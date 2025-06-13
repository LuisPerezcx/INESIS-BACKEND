package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_escolaridad")
public class CatEscolaridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_escolaridad")
    private Long id;

    @Column(name = "nombre_escolaridad")
    private String nombreEscolaridad;
}
