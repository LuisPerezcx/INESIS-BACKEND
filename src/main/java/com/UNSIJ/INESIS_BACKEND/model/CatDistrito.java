package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_distrito")
public class CatDistrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_distrito")
    private Long id;

    @Column(name = "nombre_distrito", unique = true, nullable = false)
    private String nombreDistrito;

    @ManyToOne
    @JoinColumn(name = "id_cat_region", nullable = false)
    private CatRegion region;
}
