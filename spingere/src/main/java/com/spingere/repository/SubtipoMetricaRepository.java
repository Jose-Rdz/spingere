package com.spingere.repository;

import com.spingere.model.SubtipoMetrica;
import com.spingere.model.SubtipoMetricaPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jose-Rdz
 */
public interface SubtipoMetricaRepository extends JpaRepository<SubtipoMetrica, SubtipoMetricaPK> {
    
}
