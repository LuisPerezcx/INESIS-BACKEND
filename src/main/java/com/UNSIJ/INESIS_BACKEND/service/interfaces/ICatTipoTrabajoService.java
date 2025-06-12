package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.CatTipoTrabajo;

import java.util.List;

public interface ICatTipoTrabajoService {
    List<CatTipoTrabajo> findAll();

    CatTipoTrabajo findById(Long id);
}
