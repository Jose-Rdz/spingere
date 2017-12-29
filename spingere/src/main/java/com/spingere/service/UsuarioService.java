package com.spingere.service;

import com.spingere.dto.UsuarioDto;
import com.spingere.utils.SpingereException;

/**
 *
 * @author Jose-Rdz
 */
public interface UsuarioService {

    /**
     * 
     * @param dto
     * @throws SpingereException 
     */
    void saveUsuario(UsuarioDto dto) throws SpingereException;
    
}
