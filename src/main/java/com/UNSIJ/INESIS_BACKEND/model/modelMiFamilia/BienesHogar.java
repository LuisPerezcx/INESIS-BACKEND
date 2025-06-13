package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bienes_hogar")
public class BienesHogar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bienes_hogar")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mi_familia")
    private MiFamilia miFamilia;

    @ManyToOne
    @JoinColumn(name = "id_cat_bienes_hogar")
    private CatBienesHogar catBienHogar;
}
