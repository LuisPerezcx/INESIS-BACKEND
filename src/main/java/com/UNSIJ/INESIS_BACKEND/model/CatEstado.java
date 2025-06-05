package com.UNSIJ.INESIS_BACKEND.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_estado")
public class CatEstado {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_estado")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private AlumnoModel alumno;
    
    @Column( name = "estatus", nullable = false)
    private String estatus;
}
