package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_internet")
public class CatInternet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_internet")
    private Long id;

    @Column(name = "nombre_internet")
    private String nombreInternet;
}
