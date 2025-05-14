package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_internet")
public class CatInternetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_internet")
    private Long id;

    @Column(name = "nombre_internet")
    private String nombreInternet;

    public void setNombreInternet(String nombreInternet) {
        this.nombreInternet = nombreInternet;
    }

    public String getNombreInternet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
