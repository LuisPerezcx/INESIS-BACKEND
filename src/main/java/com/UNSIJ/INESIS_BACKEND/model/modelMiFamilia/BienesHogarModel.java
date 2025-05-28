package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bienes_hogar")
public class BienesHogarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bienes_hogar")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mi_familia")
    private MiFamiliaModel miFamilia;

    @ManyToOne
    @JoinColumn(name = "id_cat_bienes_hogar")
    private CatBienesHogarModel catBienHogar;
}
