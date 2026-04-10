package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.CatGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatGrupoRepository extends JpaRepository<CatGrupo, Long> {
    CatGrupo findByCatCarrera_IdAndCatSemestre_Id(Long idCarrera, Long idSemestre);
    Optional <CatGrupo> findByNombreGrupo(String nombreGrupo);
    List<CatGrupo> findAllByNombreGrupo(String nombreGrupo);
}
