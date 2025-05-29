/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.UNSIJ.INESIS_BACKEND.model.modelMiFamilia;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_servicio_otro")

/**
 *
 * @author Alumnos
 */
public class CatServiciosOtro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio_otro")
    private Long id;

    @Column(name = "nombre_servicio", nullable = false, unique = true)
    private String nombreServicio;
}
