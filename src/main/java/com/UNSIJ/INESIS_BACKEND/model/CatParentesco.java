package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_parentesco")
public class CatParentesco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_parentesco")
    private Long id;

    @Column(name = "nombre_parentesco")
    private String nombreParentesco;
}
