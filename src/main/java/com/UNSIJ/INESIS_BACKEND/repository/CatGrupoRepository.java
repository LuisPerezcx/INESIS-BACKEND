package com.UNSIJ.INESIS_BACKEND.repository;

import com.UNSIJ.INESIS_BACKEND.model.CatGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatGrupoRepository extends JpaRepository<CatGrupo, Long> {
    CatGrupo findByCatCarrera_IdAndCatSemestre_Id(Long idCarrera, Long idSemestre);

}
