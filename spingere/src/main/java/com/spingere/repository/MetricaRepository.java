package com.spingere.repository;

import com.spingere.model.Metrica;
import com.spingere.model.MetricaPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jose-Rdz
 */
public interface MetricaRepository extends JpaRepository<Metrica, MetricaPK> {
    
}
