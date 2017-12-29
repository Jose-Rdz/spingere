package com.spingere.service.impl;

import java.util.List;
import com.spingere.dto.UsuarioDto;
import com.spingere.model.ClienteUsuario;
import com.spingere.model.Usuario;
import com.spingere.repository.ClienteUsuarioRepository;
import com.spingere.repository.UsuarioRepository;
import com.spingere.utils.SpingereException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spingere.service.UsuarioService;

/**
 *
 * @author Jose-Rdz
 * @author franvazgom
 */
@Repository
@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    
    private @Autowired UsuarioRepository usuarioRepository;
    private @Autowired ClienteUsuarioRepository cuRepository;

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveUsuario(UsuarioDto dto) throws SpingereException {
        try {
            logger.debug("dto = " + dto.toString());
            //Guarda / Actualiza Usuario
            Usuario user = new Usuario();
            Integer idUsuario = dto.getIdUsuario(); 
            user.setApellidoPaterno(dto.getApPaterno());
            user.setApellidoMaterno(dto.getApMaterno());
            user.setContrasena(dto.getContrasena());
            user.setUsuario(dto.getUsuario());
            user.setNombre(dto.getNombre());
            user.setCorreoUsuario(dto.getEmail()); 
            if (idUsuario != null) {
                user.setIdUsuario(idUsuario);
            }
            logger.debug(user.toString());
            usuarioRepository.saveAndFlush(user);
            
            //Guarda la relación con el cliente
            ClienteUsuario newCU = new ClienteUsuario(); 
            newCU.setIdCliente(dto.getTipoCliente());
            newCU.setIdUsuario(user.getIdUsuario());
            if (dto.getTipoCliente().equals(1)) {
                newCU.setIdRolUsuario(1);
            } else {
                newCU.setIdRolUsuario(2);
            }
            cuRepository.save(newCU); 
            logger.info("{{ usuario guardado correctamente }}");
        } catch (RuntimeException re) {
            logger.error("{{ usuario no registrado en DB }}", re);
            throw new SpingereException("No fue posible guardar el usuario...");
        }
    }
    
}
