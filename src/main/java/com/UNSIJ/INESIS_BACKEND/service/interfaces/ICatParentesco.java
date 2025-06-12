package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.CatParentesco;

import java.util.List;

public interface ICatParentesco {
    List<CatParentesco> findAll();

    CatParentesco findById(Long id);
}
