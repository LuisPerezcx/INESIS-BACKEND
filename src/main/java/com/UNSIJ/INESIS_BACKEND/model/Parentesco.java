package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name = "cat_parentesco")

public class Parentesco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_parentesco")
    private Long id;

    @NotNull
    @Column(name = "nombre_parentesco")
    private String nombreParentesco;
}
