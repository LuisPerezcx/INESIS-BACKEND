package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_semestre")
public class CatSemestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_semestre")
    private Long id;

    

    @Column(name = "nombre_semestre")
    private String nombreSemestre;
}
