package com.spingere.service.impl;

import com.spingere.service.GraficaService;
import com.spingere.utils.SpingereException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose-Rdz
 */
@Service
@Repository
public class GraficaServiceImpl implements GraficaService {
    
    private static final Logger logger = LoggerFactory.getLogger(GraficaServiceImpl.class);
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional(readOnly = true)
    public String getGraficaHtml(Integer idCliente, Integer idProyecto, Integer idGrafica) throws SpingereException {
        logger.info("---> getGraficaHtml cliente={}, proyecto={}, grafica={}", idCliente, idProyecto, idGrafica);
        String strQuery = "Select  ele.elemento "
            + "  From     (Select       Distinct "
            + "                      ge.renglonElemento, "
            + "                         Case "
            + "                              When "
            + "                      ge.renglonMultiple         =      1 "
            + "                              Then                   cp.idProyecto "
            + "                              Else                      Null "
            + "                         End                     As     idProyecto, "
            + "                      ge.ordenElemento, "
            + "                         Concat( "
            + "                         Case "
            + "                              When "
            + "                    (gea.idTipoAtributo          =  att.minIdTipoAtributo      or "
            + "                     gea.idTipoAtributo          Is     NULL             )     and "
            + "                    ( ge.inicioAgrupacion        =      1                      or "
            + "                      ge.inicioAgrupacion        Is     NULL             ) "
            + "                              Then                      Concat('<', "
            + "                      tt.tipoTag) "
            + "                              When "
            + "                    (gea.idTipoAtributo          =  att.minIdTipoAtributo      or "
            + "                     gea.idTipoAtributo          Is     NULL             )     and "
            + "                     gev.valorGraficaElemento    Is     NULL                   and "
            + "                     gem.idSubTipoMetrica        Is     NULL                   and "
            + "                      ge.inicioAgrupacion        =      0 "
            + "                              Then                      Concat('</', "
            + "                      tt.tipoTag) "
            + "                              Else                     '' "
            + "                         End, "
            + "                         Case "
            + "                              When "
            + "                     gea.valorAtributo           Is     NOT NULL "
            + "                              Then                      Concat(' ', "
            + "                      ta.tipoAtributo , '=\"', "
            + "                     gea.valorAtributo, '\"') "
            + "                              Else                     '' "
            + "                         End, "
            + "                         Case "
            + "                              When "
            + "                    (gea.idTipoAtributo          =  att.maxIdTipoAtributo      or "
            + "                     gea.idTipoAtributo          Is     NULL             )     and "
            + "                     gev.valorGraficaElemento    Is     NOT NULL               and "
            + "                     gem.idSubTipoMetrica        Is     NULL "
            + "                              Then                      Concat('>', "
            + "                     gev.valorGraficaElemento, '</', "
            + "                      tt.tipoTag, '>') "
            + "                              When "
            + "                    (gea.idTipoAtributo          =  att.maxIdTipoAtributo      or "
            + "                     gea.idTipoAtributo          Is     NULL             )     and "
            + "                     gem.idSubTipoMetrica        Is     NOT NULL "
            + "                              Then                      Concat('>', "
            + "                         Case "
            + "                              When "
            + "                     gev.valorGraficaElemento    Is     NOT NULL "
            + "                              Then "
            + "                     gev.valorGraficaElemento "
            + "                              Else                     '' "
            + "                         End, "
            + "                         Case "
            + "                              When "
            + "                     gem.formatoElementoMetrica  Is     NULL "
            + "                              Then "
            + "                       m.valorMetrica "
            + "                              When "
            + "                     gem.formatoElementoMetrica  =     'decimal_2' "
            + "                              Then                      Format( "
            + "                       m.valorMetrica, 2) "
            + "                              When "
            + "                     gem.formatoElementoMetrica  =     'decimal_4' "
            + "                              Then                      Format( "
            + "                       m.valorMetrica, 4) "
            + "                         End, '</', "
            + "                      tt.tipoTag, '>') "
            + "                              When "
            + "                    (gea.idTipoAtributo          =  att.maxIdTipoAtributo      or "
            + "                     gea.idTipoAtributo          Is     NULL             )     and "
            + "                     gev.valorGraficaElemento    Is     NULL                   and "
            + "                     gem.idSubTipoMetrica        Is     NULL                   and "
            + "                      ge.inicioAgrupacion        Is     NULL "
            + "                              Then                      Concat('></', "
            + "                      tt.tipoTag, '>') "
            + "                              When "
            + "                      ge.inicioAgrupacion        Is     NOT NULL "
            + "                              Then                     '>' "
            + "                              Else                     '' "
            + "                         End "
            + "                         )                       As     elemento "
            + "              From       valso_spingere. "
            + "                         ClienteProyecto         As  cp "
            + "             Inner  Join valso_spingere. "
            + "                         Grafica                 As   g "
            + "                On    cp.idCliente               =      :idCliente "
            + "             Inner  Join valso_spingere. "
            + "                         GraficaElemento         As  ge "
            + "                On     g.idGrafica               =   ge.idGrafica "
            + "               and     g.idGrafica               =      :idGrafica "
            + "             Inner  Join valso_spingere. "
            + "                         TipoTag                 As  tt "
            + "                On    ge.idTipoTag               =   tt.idTipoTag "
            + "              Left  Join valso_spingere. "
            + "                         GraficaElementoAtributo As gea "
            + "                On    ge.idGrafica               =  gea.idGrafica "
            + "               and    ge.idGraficaElemento       =  gea.idGraficaElemento "
            + "              Left  Join valso_spingere. "
            + "                         TipoAtributo            As  ta "
            + "                On   gea.idTipoAtributo          =   ta.idTipoAtributo "
            + "              Left  Join valso_spingere. "
            + "                         GraficaElementoValor    As gev "
            + "                On    ge.idGrafica               =  gev.idGrafica "
            + "               and    ge.idGraficaElemento       =  gev.idGraficaElemento "
            + "              Left  Join valso_spingere. "
            + "                         GraficaElementoMetrica  As gem "
            + "                On    ge.idGrafica               =  gem.idGrafica "
            + "               and    ge.idGraficaElemento       =  gem.idGraficaElemento "
            + "              Left  Join valso_spingere. "
            + "                         Metrica                 As   m "
            + "                On   gem.idTipoMetrica           =    m.idTipoMetrica "
            + "               and   gem.idSubTipoMetrica        =    m.idSubTipoMetrica "
            + "               and    cp.idProyecto              =    m.idProyecto "
            + "               and     m.idProyecto              In   (:idProyecto) "
            + "              Left  Join(Select      idGrafica, "
            + "                                     idGraficaElemento, "
            + "                                 Min(idTipoAtributo)         As     minIdTipoAtributo, "
            + "                                 Max(idTipoAtributo)         As     maxIdTipoAtributo, "
            + "                               Count(*)                      As     totIdTipoAtributo "
            + "                           From "
            + "                            valso_spingere.GraficaElementoAtributo "
            + "                          Where      idGrafica               =      :idGrafica "
            + "                          Group  By  idGrafica, "
            + "                                     idGraficaElemento      )As att "
            + "                On    ge.idGrafica               =  att.idGrafica "
            + "               and    ge.idGraficaElemento       =  att.idGraficaElemento "
            + "              Left  Join(Select      idProyecto, "
            + "                                     idTipoMetrica, "
            + "                                     idSubtipoMetrica, "
            + "                                 Min(fechaMetrica)           As     fechaMetrica "
            + "                           From "
            + "                            valso_spingere.Metrica "
            + "                          Where      idProyecto              In    (:idProyecto) "
            + "                          Group  By  idProyecto, "
            + "                                     idTipoMetrica, "
            + "                                     idSubtipoMetrica)       As met "
            + "                On     m.idProyecto              =  met.idProyecto "
            + "               and     m.idTipoMetrica           =  met.idTipoMetrica "
            + "               and     m.idSubtipoMetrica        =  met.idSubtipoMetrica "
            + "               and     m.fechaMetrica            =  met.fechaMetrica "
            + "             Order By ge.idGrafica, "
            + "                      ge.renglonElemento, "
            + "                         2, "
            + "                      ge.ordenElemento, "
            + "                     gea.idTipoAtributo, "
            + "                      ge.idGraficaElemento)      As ele;";
        
        try {
            Query query = em.createNativeQuery(strQuery)
                    .setParameter("idCliente", idCliente)
                    .setParameter("idProyecto", idProyecto)
                    .setParameter("idGrafica", idGrafica);
            
            List<String> resultList = query.getResultList();
            StringBuilder html = new StringBuilder();
            
            resultList.forEach((s) -> {
                html.append(s);
            });
            
            return html.toString();
        } catch (RuntimeException re) {
            logger.error("{error al recuperar la grafica desde DB}", re);
            throw new SpingereException("No fue posible recuperar la gráfica");
        }
    }
    
}
