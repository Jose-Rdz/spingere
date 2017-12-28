package com.spingere.service.impl;

import java.util.List;
import com.spingere.model.Cliente;
import com.spingere.repository.ClienteRepository;
import com.spingere.service.ClienteService;
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
 * @author franvazgom
 */
@Repository
@Service
public class ClienteServiceImpl implements ClienteService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    private @Autowired ClienteRepository clienteRepository;
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getTipoClientes() throws SpingereException {
        try {
            List<Cliente> lcu = clienteRepository.findAll(new Sort(Sort.Direction.ASC, "idCliente"));
            for (Cliente cliente : lcu) {
                logger.info(cliente.toString());
            }
            return lcu; 
        } catch (RuntimeException re) {
            logger.error("{{ Tipo clientes no recuperados desde DB }}");
            throw new SpingereException("No fue posible obtener los tipo de clientes registrados...");
        }
    }
    
}
