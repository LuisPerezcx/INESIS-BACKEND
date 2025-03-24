package com.UNSIJ.INESIS_BACKEND.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UNSIJ.INESIS_BACKEND.model.CatCarreraModel;
import com.UNSIJ.INESIS_BACKEND.model.Ejemplo;
import com.UNSIJ.INESIS_BACKEND.repository.CatCarreraRepository;
import com.UNSIJ.INESIS_BACKEND.utils.JsonUtils;

@Service
public class CatCarreraServiceJPA {
    @Autowired
    private CatCarreraRepository catCarreraRepository;

    
}
