package com.UNSIJ.INESIS_BACKEND.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel> findByAlumnoId(Long idAlumno);
}
