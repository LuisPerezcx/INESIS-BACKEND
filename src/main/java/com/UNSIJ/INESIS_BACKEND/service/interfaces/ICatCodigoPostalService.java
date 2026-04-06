package com.UNSIJ.INESIS_BACKEND.service.interfaces;

import com.UNSIJ.INESIS_BACKEND.model.CodigoPostal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ICatCodigoPostalService {
    List<CodigoPostal> findByCp(String cp);
    Map<String, Object> importarDesdeExcel(MultipartFile file) throws Exception;
}