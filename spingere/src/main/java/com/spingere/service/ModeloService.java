package com.spingere.service;

import com.spingere.dto.ModeloDto;
import com.spingere.utils.SpingereException;
import java.util.List;

/**
 *
 * @author Jose-Rdz
 */
public interface ModeloService {
    
    /**
     * 
     * @param idCliente
     * @param idProyecto
     * @param idModelo 
     * @return  
     * @throws com.spingere.utils.SpingereException 
     */
    List<ModeloDto> getDataModelo(Integer idCliente, Integer idProyecto, Integer idModelo) throws SpingereException;
    
}
