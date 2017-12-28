package com.spingere.service.impl;

import java.util.List;
import com.spingere.dto.UserDto;
import com.spingere.model.ClienteUsuario;
import com.spingere.model.Usuario;
import com.spingere.repository.ClienteUsuarioRepository;
import com.spingere.repository.UsuarioRepository;
import com.spingere.service.UserService;
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
public class UserServiceImpl implements UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    private @Autowired UsuarioRepository usuarioRepository;
    private @Autowired ClienteUsuarioRepository cuRepository;
    

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveUser(UserDto dto) throws SpingereException {
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
            if (idUsuario != null)
                user.setIdUsuario(idUsuario);
            logger.debug(user.toString());
            usuarioRepository.saveAndFlush(user);
            
            //Guarda la relación con el cliente
            ClienteUsuario newCU = new ClienteUsuario(); 
            newCU.setIdCliente(dto.getTipoCliente());
            newCU.setIdUsuario(user.getIdUsuario());
            if (dto.getTipoCliente().equals(1))
                newCU.setIdRolUsuario(1);
            else
                newCU.setIdRolUsuario(2);
            cuRepository.save(newCU); 
            
            logger.debug("{{ usuario guardado correctamente }}");
        } catch (RuntimeException re) {
            logger.error("{{ usuario no registrado en DB }}", re);
            throw new SpingereException("No fue posible guardar el usuario...");
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsers() throws SpingereException {
        try {
            return usuarioRepository.findAll(new Sort(Sort.Direction.ASC, "nombre"));
        } catch (RuntimeException re) {
            logger.error("{{ usuarios no recuperados desde DB }}");
            throw new SpingereException("No fue posible obtener los usuarios registrados...");
        }
    }
    
}
