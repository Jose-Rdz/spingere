package com.spingere.repository;

import com.spingere.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author AnGeL
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * 
     * @param usuario
     * @return 
     */
    Optional<Usuario> findByUsuario(String usuario);
    
}