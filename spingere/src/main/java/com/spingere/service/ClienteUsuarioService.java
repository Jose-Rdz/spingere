package com.spingere.service;

import java.util.List;
import com.spingere.model.ClienteUsuario;
import com.spingere.utils.SpingereException;


public interface ClienteUsuarioService {

    /**
     * 
     * @return
     * @throws SpingereException 
     */
    List<ClienteUsuario> getClients() throws SpingereException;
        
    
}
