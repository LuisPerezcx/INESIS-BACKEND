package com.UNSIJ.INESIS_BACKEND.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.UNSIJ.INESIS_BACKEND.model.CatGrupoModel;

public interface CatGrupoRepository extends JpaRepository<CatGrupoModel, Long> {
    CatGrupoModel findByCatCarreraModel_IdAndCatSemestreModel_Id(Long idCarrera, Long idSemestre);

}
