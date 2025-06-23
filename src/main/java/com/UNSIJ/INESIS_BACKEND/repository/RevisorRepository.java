package com.UNSIJ.INESIS_BACKEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.Revisor;

public interface RevisorRepository extends JpaRepository<Revisor, Long> {
    
    // Método para verificar si ya existe un revisor con la misma matrícula
    boolean existsByMatricula(String matricula);
}
