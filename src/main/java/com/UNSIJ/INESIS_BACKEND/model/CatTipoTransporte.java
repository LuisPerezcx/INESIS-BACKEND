package com.UNSIJ.INESIS_BACKEND.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cat_tipo_transporte")
public class CatTipoTransporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCatTipoTransporte;

    @Column(name = "nombre_tipo")
    private String nombreTipo;
}
