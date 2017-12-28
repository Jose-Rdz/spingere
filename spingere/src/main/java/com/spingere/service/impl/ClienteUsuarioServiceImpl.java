package com.spingere.service.impl;

import java.util.List;
import com.spingere.model.ClienteUsuario;
import com.spingere.repository.ClienteUsuarioRepository;
import com.spingere.service.ClienteUsuarioService;
import com.spingere.utils.SpingereException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author G13380
 */
@Repository
@Service
public class ClienteUsuarioServiceImpl implements ClienteUsuarioService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    private @Autowired ClienteUsuarioRepository clienteUsuarioRepository;
    
    
    @Override
    @Transactional(readOnly = true)
    public List<ClienteUsuario> getClients() throws SpingereException {
        try {
            List<ClienteUsuario> lcu = clienteUsuarioRepository.findAll(new Sort(Sort.Direction.ASC, "idCliente"));
            for (ClienteUsuario clienteUsuario : lcu) {
                logger.info(clienteUsuario.toString());
            }
            return lcu; 
        } catch (RuntimeException re) {
            logger.error("{{ usuarios no recuperados desde DB }}");
            throw new SpingereException("No fue posible obtener los usuarios registrados...");
        }
    }
    
}
