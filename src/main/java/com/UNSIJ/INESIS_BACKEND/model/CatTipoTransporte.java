package com.UNSIJ.INESIS_BACKEND.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_tipo_transporte")
public class CatTipoTransporte {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCatTipoTransporte;

    @Column(name = "nombre_tipo")
    private String nombreTipo;

    @OneToMany(mappedBy = "catTipoTransporte")
    @JsonIgnore
    private List<Transporte> transportes;
}
