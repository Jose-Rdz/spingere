package com.spingere.repository;

import com.spingere.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Franvazgom
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}