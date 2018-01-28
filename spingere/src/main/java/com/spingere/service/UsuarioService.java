package com.spingere.service;

import com.spingere.dto.UsuarioDto;
import com.spingere.model.Cliente;
import com.spingere.utils.SpingereException;
import java.util.List;

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
    
    /**
     * 
     * @param usuario
     * @return 
     */
    List<Cliente> getClientesUsuario(String usuario);
    
}
