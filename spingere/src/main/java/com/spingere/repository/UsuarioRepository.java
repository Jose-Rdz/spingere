package com.spingere.repository;

import com.spingere.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author AnGeL
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}