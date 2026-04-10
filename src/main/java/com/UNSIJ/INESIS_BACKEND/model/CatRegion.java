package com.UNSIJ.INESIS_BACKEND.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cat_region")
public class CatRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_region")
    private Long id;

    @Column(name = "nombre_region", unique = true, nullable = false)
    private String nombreRegion;

    @JsonIgnore
    @OneToMany(mappedBy = "region")
    private List<CatDistrito> distritos;
}
