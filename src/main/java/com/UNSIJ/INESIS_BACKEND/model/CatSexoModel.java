package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_sexo")
public class CatSexoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_sexo")
    private Long id;

    @Column(name = "nombre_sexo")
    private String nombreSexo;
}
