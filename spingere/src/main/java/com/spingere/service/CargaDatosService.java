package com.spingere.service;

import com.spingere.utils.SpingereException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author AnGeL
 */
public interface CargaDatosService {
    
    /**
     * Realiza la carga masiva de métricas a la DB desde un archivo xls
     * @param file archivo xls
     * @throws SpingereException 
     */
    void cargaDatosFromXls(MultipartFile file) throws SpingereException;
    
}
