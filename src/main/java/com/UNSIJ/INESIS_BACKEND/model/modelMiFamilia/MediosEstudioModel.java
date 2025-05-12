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
@Table(name = "medios_estudio")
public class MediosEstudioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medios_estudio")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cat_medios_estudios")
    private CatMediosEstudioModel categoriaMedio;
}
