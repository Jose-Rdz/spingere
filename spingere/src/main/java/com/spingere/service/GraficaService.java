package com.spingere.service;

import com.spingere.dto.GraficaBarrasDto;
import com.spingere.utils.SpingereException;
import java.util.List;

/**
 *
 * @author Jose-Rdz
 */
public interface GraficaService {
    
    /**
     * 
     * @param idCliente
     * @param idProyecto
     * @param idGrafica
     * @return
     * @throws SpingereException 
     */
    String getGraficaHtml(Integer idCliente, Integer idProyecto, Integer idGrafica) throws SpingereException;
    
    /**
     * 
     * @param idGrafica
     * @param idSubtipoGrafica
     * @param idProyecto
     * @return
     * @throws SpingereException 
     */
    List<GraficaBarrasDto> getDatosGraficaBarras(Integer idGrafica, Integer idSubtipoGrafica, Integer idProyecto) throws SpingereException;
    
}
