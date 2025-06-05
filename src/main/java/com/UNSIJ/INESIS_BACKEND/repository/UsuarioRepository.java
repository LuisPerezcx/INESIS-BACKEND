package com.UNSIJ.INESIS_BACKEND.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByAlumnoId(Long idAlumno);

    Optional<Usuario> findByUsuario(String usuario);
}
