package com.spingere.service.impl;

import com.spingere.dto.UsuarioDto;
import com.spingere.model.Cliente;
import com.spingere.model.ClienteUsuario;
import com.spingere.model.Usuario;
import com.spingere.repository.ClienteUsuarioRepository;
import com.spingere.repository.UsuarioRepository;
import com.spingere.utils.SpingereException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spingere.service.UsuarioService;

/**
 *
 * @author Jose-Rdz
 * @author franvazgom
 */
@Service
@Repository
public class UsuarioServiceImpl implements UsuarioService {
    
    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    
    private @Autowired ClienteUsuarioRepository cuRepository;
    private @Autowired UsuarioRepository usuarioRepository;
    private @Autowired PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = SpingereException.class)
    public void saveUsuario(UsuarioDto dto) throws SpingereException {
        try {
            logger.info("dto={}", dto);
            
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(dto.getIdUsuario());
            usuario.setApellidoPaterno(dto.getApPaterno());
            usuario.setApellidoMaterno(dto.getApMaterno());
            usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
            usuario.setUsuario(dto.getUsuario());
            usuario.setNombre(dto.getNombre());
            usuario.setCorreoUsuario(dto.getEmail()); 
            usuario.setActivo(true);
            
            logger.info("usuario={}", usuario);
            
            usuarioRepository.saveAndFlush(usuario);
            
            //Guarda la relación con el cliente
            ClienteUsuario newCU = new ClienteUsuario(); 
            newCU.setIdCliente(dto.getTipoCliente());
            newCU.setIdUsuario(usuario.getIdUsuario());
            newCU.setIdRolUsuario(dto.getTipoCliente().equals(1) ? 1 : 2);
            cuRepository.saveAndFlush(newCU);
            
            logger.info("{{ usuario guardado correctamente }}");
        } catch (RuntimeException re) {
            logger.error("{{ usuario no registrado en DB }}", re);
            throw new SpingereException("No fue posible guardar el usuario...");
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientesUsuario(String usuario) {
        try {
            Optional<Usuario> optional = usuarioRepository.findByUsuario(usuario);
            Usuario u = optional.get();
            List<Cliente> clientes = u.getClientes();
            clientes.size();
            return clientes;
        } catch (NoSuchElementException nse) {
            return new ArrayList<>();
        }
    }
    
}
