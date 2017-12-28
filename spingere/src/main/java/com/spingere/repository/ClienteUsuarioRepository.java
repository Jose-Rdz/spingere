package com.spingere.repository;

import com.spingere.model.ClienteUsuario;
import com.spingere.model.ClienteUsuarioPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Franvazgom
 */
public interface ClienteUsuarioRepository extends JpaRepository<ClienteUsuario, ClienteUsuarioPK> {

}