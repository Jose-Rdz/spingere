package com.spingere.service;

import com.spingere.model.Cliente;
import com.spingere.model.ClienteUsuario;
import com.spingere.model.Grafica;
import com.spingere.model.Proyecto;
import com.spingere.model.Usuario;
import com.spingere.utils.SpingereException;
import java.util.List;

/**
 *
 * @author Jose-Rdz
 */
public interface CatalogoService {
    
    List<Cliente> catAllTiposCliente() throws SpingereException;
    
    List<ClienteUsuario> catAllClientesUsuarios() throws SpingereException;
    
    List<Usuario> catAllUsuarios() throws SpingereException;
    
    List<Grafica> catAllGraficas() throws SpingereException;
    
    List<Proyecto> catProyectosCliente(Integer idCliente) throws SpingereException;
    
}
