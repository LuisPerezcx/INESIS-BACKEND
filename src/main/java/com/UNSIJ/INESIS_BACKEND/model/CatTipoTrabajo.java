package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_tipo_trabajo")
public class CatTipoTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_tipo_trabajo")
    private Long id;

    @NotNull
    @Column(name = "nombre_tipo")
    private String nombreTipo;
}
