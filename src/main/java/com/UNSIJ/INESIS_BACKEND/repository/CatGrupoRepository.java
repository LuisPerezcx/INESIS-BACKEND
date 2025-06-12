package com.UNSIJ.INESIS_BACKEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.CatGrupo;

public interface CatGrupoRepository extends JpaRepository<CatGrupo, Long> {
    CatGrupo findByCatCarrera_IdAndCatSemestre_Id(Long idCarrera, Long idSemestre);

}
