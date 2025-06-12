package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_tipo_vivienda")
public class CatTipoVivienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_tipo_vivienda")
    private Long id;

    @Column(name = "nombre_tipo")
    private String nombreTipo;

}
