package com.spingere.service;

import com.spingere.utils.SpingereException;

/**
 *
 * @author Jose-Rdz
 */
public interface GraficaService {
    
    String getGraficaHtml(Integer idCliente, Integer idProyecto, Integer idGrafica) throws SpingereException;
    
}
