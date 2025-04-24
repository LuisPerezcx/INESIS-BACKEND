package com.UNSIJ.INESIS_BACKEND.model;

import java.util.List;

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

    private String nombreTipo;
    private String abrevTipo;

    @OneToMany(mappedBy = "catTipoTransporte")
    private List<Transporte> transportes;
}
