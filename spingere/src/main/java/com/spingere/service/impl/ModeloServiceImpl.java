package com.spingere.service.impl;

import com.spingere.dto.ModeloDto;
import com.spingere.service.ModeloService;
import com.spingere.utils.SpingereException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose-Rdz
 */
@Repository
@Service
public class ModeloServiceImpl implements ModeloService {
    
    private static final Logger logger = LoggerFactory.getLogger(ModeloServiceImpl.class);
    
    private @Autowired DataSource dataSource;
    
    @Override
    @Transactional
    public List<ModeloDto> getDataModelo(Integer idCliente, Integer idProyecto, Integer idModelo) throws SpingereException {
        logger.info("---> getModelo cliente={}, proyecto={}, modelo={}", idCliente, idProyecto, idModelo);
        
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("graficaNormal")
                .withCatalogName("valso_spingere")
                .withoutProcedureColumnMetaDataAccess()
                .returningResultSet("data_modelo", BeanPropertyRowMapper.newInstance(ModeloDto.class))
                .declareParameters(new SqlParameter("cliente", Types.INTEGER))
                .declareParameters(new SqlParameter("proyecto", Types.INTEGER))
                .declareParameters(new SqlParameter("grafica", Types.INTEGER));
        
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("cliente", idCliente)
                .addValue("proyecto", idProyecto)
                .addValue("grafica", idModelo);
        
        Map<String, Object> resultMap = simpleJdbcCall.execute(in);
        
        logger.info("resultadoSP --> {}", resultMap);

        return (List<ModeloDto>) resultMap.get("data_modelo");
    }
    
}
