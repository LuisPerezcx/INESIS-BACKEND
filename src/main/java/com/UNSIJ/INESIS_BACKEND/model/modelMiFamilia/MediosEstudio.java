package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medios_estudio")
public class MediosEstudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medios_estudio")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mi_familia", nullable = false)
    @JsonIgnore
    private MiFamilia miFamilia;

    @ManyToOne
    @JoinColumn(name = "id_cat_medios_estudios")
    private CatMediosEstudio catMediosEstudio;
}
