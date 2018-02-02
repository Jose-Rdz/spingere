package com.spingere.service;

import com.spingere.utils.SpingereException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author AnGeL
 */
public interface CargaDatosService {
    
    /**
     * Realiza la carga masiva de métricas a la DB desde un archivo xlsx
     * @param file archivo xlsx
     * @throws SpingereException 
     */
    void cargaDatosFromXlsx(MultipartFile file) throws SpingereException;
    
}
