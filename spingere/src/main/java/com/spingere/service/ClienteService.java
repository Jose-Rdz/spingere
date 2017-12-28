package com.spingere.service;

import java.util.List;
import com.spingere.model.Cliente;
import com.spingere.utils.SpingereException;


public interface ClienteService {

    /**
     * 
     * @return
     * @throws SpingereException 
     */
    List<Cliente> getTipoClientes() throws SpingereException;
        
    
}
