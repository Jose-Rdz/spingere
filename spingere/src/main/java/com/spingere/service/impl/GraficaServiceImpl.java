package com.spingere.service.impl;

import com.spingere.dto.GraficaBarrasDto;
import com.spingere.service.GraficaService;
import com.spingere.utils.SpingereException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    public List<GraficaBarrasDto> getDatosGraficaBarras(Integer idGrafica, Integer idSubtipoGrafica, Integer idProyecto) 
            throws SpingereException {
        logger.info("---> getDatosGraficaBarras grafica={}", idGrafica);
        
        //Reporte Est. Ind. Aprox y Med == 3         
        String strQuery =""; 
        strQuery = "SELECT s.nmonicoMetrica as nombre, m.valorMetrica as valor FROM Metrica m, SubtipoMetrica s " +
                            "where m.idSubtipoMetrica = s.idSubtipoMetrica " +
                            "	and m.idProyecto = :idProyecto " + 
                            "   and m.idSubtipoMetrica in ";
                
        if (idSubtipoGrafica == 1) // Tamaño Funcional - CFP
            strQuery += "	(31, 34, 37, 117); ";
        else if (idSubtipoGrafica == 2) //Tamaño Funcional - CFP - Detalle
            strQuery += "	(30, 31, 32, 33, 34, 35, 36, 37, 38, 117); ";         
        else if (idSubtipoGrafica == 4)// Estimación Esfuerzo - HH
            strQuery += "	(40, 42, 45, 118); ";
        else if (idSubtipoGrafica == 5)// Estimación Esfuerzo - HH - Detalle
            strQuery += "	(40, 41, 42, 43, 44, 45, 46, 118); ";         
        else if (idSubtipoGrafica == 6)// Product Delivery Rate - PDR
            strQuery += "	(53, 7, 57); ";         
        else if (idSubtipoGrafica == 7)// Proveedor - Fases
            strQuery += "	(64, 73, 82, 91, 100, 109, 120, 123, 126, 129, 132, 135); ";
        else if (idSubtipoGrafica == 8)// Proveedor - Fases - Detalle
            strQuery += "	(63, 64, 65, 72, 73, 74, 81, 82, 83, 90, 91, 92, 99, 100, 101, 108, 109, 110); ";
        else if (idSubtipoGrafica == 9)// Cliente - Fases
            strQuery += "	(67, 76, 85, 94, 103, 112, 120, 123, 126, 129, 132, 135); ";
        else if (idSubtipoGrafica == 10)// Cliente - Fases - Detalle
            strQuery += "	(66, 67, 68, 75, 76, 77, 84, 85, 86, 93, 94, 95, 102, 103, 104, 111, 112, 113); ";
        else if (idSubtipoGrafica == 11)// Acordada - Fases
            strQuery += "	(70, 79, 88, 97, 106, 115, 120, 123, 126, 129, 132, 135); ";
        else if (idSubtipoGrafica == 12)// Acordada - Fases - Detalle
            strQuery += "	(69, 70, 71, 78, 79, 80, 87, 88, 89, 96, 97, 98, 105, 106, 107, 114, 115, 116); ";
        else if (idSubtipoGrafica == 13)// "Medición - Fases
            strQuery += "	(120, 123, 126, 129, 132, 135); ";
        else if (idSubtipoGrafica == 14)// "Medición - Fases - Detalle
            strQuery += "	(119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136); ";
        else if (idSubtipoGrafica == 15)// Tamaño funcional CFP
            strQuery += "	(30, 31, 32, 33, 34, 35); ";
        else if (idSubtipoGrafica == 16)// Estimación Esfuerzo HH
            strQuery += "	(42, 138); ";
        else if (idSubtipoGrafica == 17)// Esfuerzo por fase
            strQuery += "	(79, 88, 97, 106, 115, 147, 156); ";
        
        try {
            Query query = em.createNativeQuery(strQuery)                    
                    .setParameter("idProyecto", idProyecto);
            List<Object[]> results = query.getResultList();
            
            List<GraficaBarrasDto> datos = new ArrayList<>();
            
            results.stream().forEach(r -> {
                datos.add(new GraficaBarrasDto((String) r[0], new BigDecimal((String) r[1])));
            });
            
            logger.info("resultList={}", results);
            
            return datos;
        } catch (RuntimeException re) {
            logger.error("{error al recuperar la grafica desde DB}", re);
            throw new SpingereException("No fue posible recuperar la gráfica");
        }
    }
    
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
                + "                     gem.elementoMetrica         Is     NULL                   and "
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
                + "                     gem.elementoMetrica         Is     NULL "
                + "                              Then                      Concat('>', "
                + "                                   Case "
                + "                                        When Length(SubString_Index(gev.valorGraficaElemento, '[FECHA]', 1))  >  0 "
                + "                                         and Length(SubString_Index(gev.valorGraficaElemento, '[FECHA]', 1))  <  Length(gev.valorGraficaElemento) "
                + "                                        Then       Concat(SubString(gev.valorGraficaElemento, 1, Length(SubString_Index(gev.valorGraficaElemento, '[FECHA]', 1))), Now()) "
                + "                                        Else                        gev.valorGraficaElemento "
                + "                                   End, "
                + "                        '</', "
                + "                      tt.tipoTag, '>') "
                + "                              When "
                + "                    (gea.idTipoAtributo          =  att.maxIdTipoAtributo      or "
                + "                     gea.idTipoAtributo          Is     NULL             )     and "
                + "                     gem.elementoMetrica         Is     NOT NULL "
                + "                              Then                      Concat('>', "
                + "                     gem.elementoMetrica, '</', "
                + "                      tt.tipoTag, '>') "
                + "                              When "
                + "                    (gea.idTipoAtributo          =  att.maxIdTipoAtributo      or "
                + "                     gea.idTipoAtributo          Is     NULL             )     and "
                + "                     gev.valorGraficaElemento    Is     NULL                   and "
                + "                     gem.elementoMetrica         Is     NULL                   and "
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
                + "                and   cp.idProyecto              =      :idProyecto "
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
                + "              Left  Join(Select  gmm.idGrafica, "
                + "                                 gmm.idGraficaElemento, "
                + "                                 gmm.idProyecto, "
                + "                                     Concat( "
                + "                                     Case "
                + "                                          When      Min(gmm.elementoMetrica)    != Max(gmm.elementoMetrica) "
                + "                                           and      Min(gmm.elementoMetrica)    Is NOT NULL "
                + "                                           and      Max(gmm.elementoMetrica)    Is NOT NULL "
                + "                                          Then      Format(Convert( "
                + "                                                    Min(gmm.elementoMetrica), decimal(20,10))  * "
                + "                                            Convert(Max(gmm.elementoMetrica), decimal(20,10)), 2) "                
                + "                                          When      Min(gmm.elementoMetrica)    Is NOT NULL "
                + "                                          Then      Min(gmm.elementoMetrica) "
                + "                                          Else             ''                   End, "
                + "                                     Case When      Min(gmm.elementoMetrica_2)  Is NOT NULL "
                + "                                          Then      Min(gmm.elementoMetrica_2) "
                + "                                          Else             ''                   End, "
                + "                                     Case When      Min(gmm.elementoMetrica_3)  Is NOT NULL "
                + "                                          Then      Min(gmm.elementoMetrica_3) "
                + "                                          Else             ''                   End, "
                + "                                     Case When      Min(gmm.elementoMetrica_4)  Is NOT NULL "
                + "                                          Then      Min(gmm.elementoMetrica_4) "
                + "                                          Else             ''                   End)    As   elementoMetrica "
                + "                           From     (Select "
                + "                                         mf.idGrafica, "
                + "                                         mf.idGraficaElemento, "
                + "                                         mf.idProyecto, "
                + "                                            Concat(Case "
                + "                                                        When                             gm3.posIniElemenMetrica    =     Length(SubString_Index(gm3.valorGraficaElemento,  sm.nmonicoMetrica,  1)) - 1 "
                + "                                                        Then                   SubString(gm3.valorGraficaElemento, 1, gm3.posIniElemenMetrica) "
                + "                                                        When                             gm3.posIniElemenMetrica_4  Is   NULL "
                + "                                                        Then                                '' "
                + "                                                   End, "
                + "                                                   Case When                              mf.formatoElementoMetrica Is   NULL "
                + "                                                        Then                               m.valorMetrica "
                + "                                                        When                              mf.formatoElementoMetrica =   'decimal_2' "
                + "                                                        Then                      Format(  m.valorMetrica, 2) "
                + "                                                        When                              mf.formatoElementoMetrica =   'decimal_4' "
                + "                                                        Then                      Format(  m.valorMetrica, 4) "
                + "                                                        When                              mf.formatoElementoMetrica =   'DATE' "
                + "                                                        Then  Date(FROM_UNIXTIME(60*60*24*(m.valorMetrica - 25568))) "
                + "                                                        When                              mf.formatoElementoMetrica =   '%' "
                + "                                                        Then                      Concat(  m.valorMetrica * 100, '%') "
                + "                                                   End)                               As     elementoMetrica, "
                + "                                            Concat(Case "
                + "                                                        When                             gm3.posIniElemenMetrica_2  =    Length(SubString_Index(gm3.valorGraficaElemento,  sm.nmonicoMetrica,  1)) - 1 "
                + "                                                         and                             gm3.posFinElemenMetrica    Is   NOT NULL "
                + "                                                        Then                   SubString(gm3.valorGraficaElemento, gm3.posFinElemenMetrica,   gm3.posIniElemenMetrica_2 - gm3.posFinElemenMetrica   + 1) "
                + "                                                        Else                                '' "
                + "                                                   End, "
                + "                                                   Case When                             gm3.posFinElemenMetrica_2  Is   NULL "
                + "                                                        Then                                '' "
                + "                                                        When                              mf.formatoElementoMetrica Is   NULL "
                + "                                                        Then                               m.valorMetrica "
                + "                                                        When                              mf.formatoElementoMetrica =   'decimal_2' "
                + "                                                        Then                      Format(  m.valorMetrica, 2) "
                + "                                                        When                              mf.formatoElementoMetrica =   'decimal_4' "
                + "                                                        Then                      Format(  m.valorMetrica, 4) "
                + "                                                   End)                               As     elementoMetrica_2, "
                + "                                            Concat(Case                   Length(SubString_Index( "
                + "                                               gm3.valorGraficaElemento,  sm.nmonicoMetrica,  1)) - 1 "
                + "                                                        When                             gm3.posIniElemenMetrica_3 "
                + "                                                        Then Case When                   gm3.posFinElemenMetrica_2  Is   NOT NULL "
                + "                                                                  Then         SubString(gm3.valorGraficaElemento, gm3.posFinElemenMetrica_2, gm3.posIniElemenMetrica_3 - gm3.posFinElemenMetrica_2 + 1) "
                + "                                                                  When                   gm3.posFinElemenMetrica    Is   NOT NULL "
                + "                                                                  Then         SubString(gm3.valorGraficaElemento, gm3.posFinElemenMetrica,   gm3.posIniElemenMetrica_3 - gm3.posFinElemenMetrica   + 1) "
                + "                                                             End "
                + "                                                        Else                                '' "
                + "                                                   End, "
                + "                                                   Case When                             gm3.posFinElemenMetrica_3  Is   NULL "
                + "                                                        Then                                '' "
                + "                                                        When                              mf.formatoElementoMetrica Is   NULL "
                + "                                                        Then                               m.valorMetrica "
                + "                                                        When                              mf.formatoElementoMetrica =   'decimal_2' "
                + "                                                        Then                      Format(  m.valorMetrica, 2) "
                + "                                                        When                              mf.formatoElementoMetrica =   'decimal_4' "
                + "                                                        Then                      Format(  m.valorMetrica, 4) "
                + "                                                   End)                               As     elementoMetrica_3, "
                + "                                            Concat(Case                   Length(SubString_Index( "
                + "                                               gm3.valorGraficaElemento,  sm.nmonicoMetrica,  1)) - 1 "
                + "                                                        When                             gm3.posIniElemenMetrica_4 "
                + "                                                        Then Case When                   gm3.posFinElemenMetrica_3  Is   NOT NULL "
                + "                                                                  Then         SubString(gm3.valorGraficaElemento, gm3.posFinElemenMetrica_3, gm3.posIniElemenMetrica_4 - gm3.posFinElemenMetrica_3 + 1) "
                + "                                                                  When                   gm3.posFinElemenMetrica_2  Is   NOT NULL "
                + "                                                                  Then         SubString(gm3.valorGraficaElemento, gm3.posFinElemenMetrica_2, gm3.posIniElemenMetrica_4 - gm3.posFinElemenMetrica_2 + 1) "
                + "                                                                  When                   gm3.posFinElemenMetrica    Is   NOT NULL "
                + "                                                                  Then         SubString(gm3.valorGraficaElemento, gm3.posFinElemenMetrica,   gm3.posIniElemenMetrica_4 - gm3.posFinElemenMetrica   + 1) "
                + "                                                             End "
                + "                                                        Else                                '' "
                + "                                                   End, "
                + "                                                   Case When                             gm3.posFinElemenMetrica_4  Is   NULL "
                + "                                                        Then                                '' "
                + "                                                        When                              mf.formatoElementoMetrica Is   NULL "
                + "                                                        Then                               m.valorMetrica "
                + "                                                        When                              mf.formatoElementoMetrica =   'decimal_2' "
                + "                                                        Then                      Format(  m.valorMetrica, 2) "
                + "                                                        When                              mf.formatoElementoMetrica =   'decimal_4' "
                + "                                                        Then                      Format(  m.valorMetrica, 4) "
                + "                                                   End, "
                + "                                                   Case When                             gm3.posFinElemenMetrica_4  Is   NOT NULL "
                + "                                                        Then                   SubString(gm3.valorGraficaElemento, gm3.posFinElemenMetrica_4, Length(gm3.valorGraficaElemento) - gm3.posFinElemenMetrica_4) "
                + "                                                        When                             gm3.posFinElemenMetrica    Is   NOT NULL "
                + "                                                        Then                   SubString(gm3.valorGraficaElemento, gm3.posFinElemenMetrica,   Length(gm3.valorGraficaElemento) - gm3.posFinElemenMetrica) "
                + "                                                        Else                                '' "
                + "                                                   End)                               As     elementoMetrica_4 "
                + "                                       From          valso_spingere.Metrica                 As   m "
                + "                                      Inner Join (Select  gem.idGrafica, "
                + "                                                          gem.idGraficaElemento, "
                + "                                                          gem.formatoElementoMetrica, "
                + "                                                            m.idProyecto, "
                + "                                                            m.idTipoMetrica, "
                + "                                                            m.idSubtipoMetrica, "
                + "                                                        Min(m.fechaMetrica)           As     fechaMetrica "
                + "                                                    From "
                + "                                                     valso_spingere.Metrica                 As   m, "
                + "                                                     valso_spingere.GraficaElementoMetrica  As gem, "
                + "                                                     valso_spingere.ClienteProyecto         As  cp "
                + "                                                   Where  gem.idGrafica               =      :idGrafica "
                + "                                                     and   cp.idCliente               =      :idCliente "
                + "                                                     and (  m.idProyecto              =      :idProyecto "
                + "                                                      or  gem.idGrafica               In    (2)) "
                + "                                                     and    m.idProyecto              =   cp.idProyecto "
                + "                                                     and    m.idTipoMetrica           =  gem.idTipoMetrica "
                + "                                                     and    m.idSubtipoMetrica        =  gem.idSubtipoMetrica "
                + "                                                   Group By "
                + "                                                          gem.idGrafica, "
                + "                                                          gem.idGraficaElemento, "
                + "                                                          gem.formatoElementoMetrica, "
                + "                                                            m.idProyecto, "
                + "                                                            m.idTipoMetrica, "
                + "                                                            m.idSubtipoMetrica)       As  mf "
                + "                                         On     m.idProyecto              =   mf.idProyecto "
                + "                                        and     m.idTipoMetrica           =   mf.idTipoMetrica "
                + "                                        and     m.idSubtipoMetrica        =   mf.idSubtipoMetrica "
                + "                                        and     m.fechaMetrica            =   mf.fechaMetrica "
                + "                                      Inner Join     valso_spingere.SubtipoMetrica          As  sm "
                + "                                         On     m.idTipoMetrica           =   sm.idTipoMetrica "
                + "                                        and     m.idSubtipoMetrica        =   sm.idSubtipoMetrica "
                + "                                      Inner Join (Select  gm2.idGrafica, "
                + "                                                          gm2.idGraficaElemento, "
                + "                                                          gm2.valorGraficaElemento, "
                + "                                                          gm2.posIniElemenMetrica, "
                + "                                                          gm2.posFinElemenMetrica, "
                + "                                                          Min(Length(SubString_Index( "
                + "                                                          gm2.valorGraficaElemento, "
                + "                                                           sm.nmonicoMetrica,  1))-1) As     posIniElemenMetrica_2, "
                + "                                                          Min(Length( "
                + "                                                          gm2.valorGraficaElemento) - Length(SubString_Index( "
                + "                                                          gm2.valorGraficaElemento, "
                + "                                                           sm.nmonicoMetrica, -1)))   As     posFinElemenMetrica_2, "
                + "                                                              Case "
                + "                                                                   When "
                + "                                                          Max(Length(SubString_Index( "
                + "                                                          gm2.valorGraficaElemento, "
                + "                                                           sm.nmonicoMetrica,  1))-1) >  Min(Length(SubString_Index( "
                + "                                                                                         gm2.valorGraficaElemento, sm.nmonicoMetrica,  1))-1) "
                + "                                                                   Then                  Max(Length(SubString_Index( "
                + "                                                                                         gm2.valorGraficaElemento, sm.nmonicoMetrica,  1))-1) "
                + "                                                                   Else                      NULL "
                + "                                                              End                     As     posIniElemenMetrica_3, "
                + "                                                              Case "
                + "                                                                   When "
                + "                                                          Max(Length( "
                + "                                                          gm2.valorGraficaElemento) - Length(SubString_Index( "
                + "                                                          gm2.valorGraficaElemento, "
                + "                                                           sm.nmonicoMetrica, -1)))   >  Min(Length( "
                + "                                                                                         gm2.valorGraficaElemento) - Length(SubString_Index( "
                + "                                                                                         gm2.valorGraficaElemento, sm.nmonicoMetrica, -1))) "
                + "                                                                   Then                  Max(Length( "
                + "                                                                                         gm2.valorGraficaElemento) - Length(SubString_Index( "
                + "                                                                                         gm2.valorGraficaElemento, sm.nmonicoMetrica, -1))) "
                + "                                                                   Else                      NULL "
                + "                                                              End                     As     posFinElemenMetrica_3, "
                + "                                                          gm2.posIniElemenMetrica_4, "
                + "                                                          gm2.posFinElemenMetrica_4 "
                + "                                                    From "
                + "                                                     valso_spingere.SubtipoMetrica          As  sm "
                + "                                                   Right Join "
                + "                                                     valso_spingere.GraficaElementoMetrica  As gm1 "
                + "                                                      On   sm.idTipoMetrica           =  gm1.idTipoMetrica "
                + "                                                     and   sm.idSubtipoMetrica        =  gm1.idSubtipoMetrica "
                + "                                                   Right Join(Select  gem.idGrafica, "
                + "                                                                      gem.idGraficaElemento, "
                + "                                                                      gev.valorGraficaElemento, "
                + "                                                                      Min(Length(SubString_Index( "
                + "                                                                      gev.valorGraficaElemento, "
                + "                                                                       sm.nmonicoMetrica,  1))-1) As     posIniElemenMetrica, "
                + "                                                                          Case "
                + "                                                                               When "
                + "                                                                      Max(Length(SubString_Index( "
                + "                                                                      gev.valorGraficaElemento, "
                + "                                                                       sm.nmonicoMetrica,  1))-1) >  Min(Length(SubString_Index( "
                + "                                                                                                     gev.valorGraficaElemento, sm.nmonicoMetrica,  1))-1) "
                + "                                                                               Then                  Max(Length(SubString_Index( "
                + "                                                                                                     gev.valorGraficaElemento, sm.nmonicoMetrica,  1))-1) "
                + "                                                                               Else                      NULL "
                + "                                                                          End                     As     posIniElemenMetrica_4, "
                + "                                                                      Min(Length( "
                + "                                                                      gev.valorGraficaElemento) - Length(SubString_Index( "
                + "                                                                      gev.valorGraficaElemento, "
                + "                                                                       sm.nmonicoMetrica, -1)))   As     posFinElemenMetrica, "
                + "                                                                          Case "
                + "                                                                               When "
                + "                                                                      Max(Length( "
                + "                                                                      gev.valorGraficaElemento) - Length(SubString_Index( "
                + "                                                                      gev.valorGraficaElemento, "
                + "                                                                       sm.nmonicoMetrica, -1)))   >  Min(Length( "
                + "                                                                                                     gev.valorGraficaElemento) - Length(SubString_Index( "
                + "                                                                                                     gev.valorGraficaElemento, sm.nmonicoMetrica, -1))) "
                + "                                                                               Then                  Max(Length( "
                + "                                                                                                     gev.valorGraficaElemento) - Length(SubString_Index( "
                + "                                                                                                     gev.valorGraficaElemento, sm.nmonicoMetrica, -1))) "
                + "                                                                               Else                      NULL "
                + "                                                                          End                     As     posFinElemenMetrica_4 "
                + "                                                                From "
                + "                                                                 valso_spingere.GraficaElementoMetrica  As gem "
                + "                                                               Inner Join "
                + "                                                                 valso_spingere.SubtipoMetrica          As  sm "
                + "                                                                  On  gem.idTipoMetrica           =   sm.idTipoMetrica "
                + "                                                                 and  gem.idSubtipoMetrica        =   sm.idSubtipoMetrica "
                + "                                                                Left Join "
                + "                                                                 valso_spingere.GraficaElementoValor    As gev "
                + "                                                                  On  gem.idGrafica               =  gev.idGrafica "
                + "                                                                 and  gem.idGraficaElemento       =  gev.idGraficaElemento "
                + "                                                               Where  gem.idGrafica               =      :idGrafica "
                + "                                                               Group By "
                + "                                                                      gem.idGrafica, "
                + "                                                                      gem.idGraficaElemento, "
                + "                                                                      gev.valorGraficaElemento)   As gm2 "
                + "                                                      On  gm1.idGrafica                           =  gm2.idGrafica "
                + "                                                     and  gm1.idGraficaElemento                   =  gm2.idGraficaElemento "
                + "                                                     and      Length(SubString_Index( "
                + "                                                          gm2.valorGraficaElemento, "
                + "                                                           sm.nmonicoMetrica,  1))-1              >  gm2.posIniElemenMetrica "
                + "                                                     and      Length(SubString_Index( "
                + "                                                          gm2.valorGraficaElemento, "
                + "                                                           sm.nmonicoMetrica,  1))-1              <  gm2.posIniElemenMetrica_4 "
                + "                                                   Group  By "
                + "                                                          gm2.idGrafica, "
                + "                                                          gm2.idGraficaElemento, "
                + "                                                          gm2.posIniElemenMetrica, "
                + "                                                          gm2.posFinElemenMetrica, "
                + "                                                          gm2.posIniElemenMetrica_4, "
                + "                                                          gm2.posFinElemenMetrica_4)  As gm3 "
                + "                                         On   gm3.idGrafica               =   mf.idGrafica "
                + "                                        and   gm3.idGraficaElemento       =   mf.idGraficaElemento) As gmm "
                + "                          Group By "
                + "                                 gmm.idGrafica, "
                + "                                 gmm.idGraficaElemento, "
                + "                                 gmm.idProyecto        )     As gem "
                + "                On    ge.idGrafica               =  gem.idGrafica "
                + "               and    ge.idGraficaElemento       =  gem.idGraficaElemento "
                + "               and    cp.idProyecto              =  gem.idProyecto "
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