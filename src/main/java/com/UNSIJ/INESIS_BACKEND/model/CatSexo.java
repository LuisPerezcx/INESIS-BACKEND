package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_sexo")
public class CatSexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_sexo")
    private Long id;

    @Column(name = "nombre_sexo")
    private String nombreSexo;
}
