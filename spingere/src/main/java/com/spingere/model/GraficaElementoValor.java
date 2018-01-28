package com.spingere.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author Jose-Rdz
 */
@Entity
@Table(name = "graficaelementovalor")
@IdClass(GraficaElementoValorPK.class)
public class GraficaElementoValor implements Serializable {

    private static final long serialVersionUID = 3622971327092323348L;
    
    @Id
    @Column
    private Integer idGrafica;
    
    @Id
    @Column
    private Integer idGraficaElemento;
    
    @Column
    private String valorGraficaElemento;

    public Integer getIdGrafica() {
        return idGrafica;
    }

    public void setIdGrafica(Integer idGrafica) {
        this.idGrafica = idGrafica;
    }

    public Integer getIdGraficaElemento() {
        return idGraficaElemento;
    }

    public void setIdGraficaElemento(Integer idGraficaElemento) {
        this.idGraficaElemento = idGraficaElemento;
    }

    public String getValorGraficaElemento() {
        return valorGraficaElemento;
    }

    public void setValorGraficaElemento(String valorGraficaElemento) {
        this.valorGraficaElemento = valorGraficaElemento;
    }

    @Override
    public String toString() {
        return "GraficaElementoValor{" + "idGrafica=" + idGrafica + ", idGraficaElemento=" + idGraficaElemento + ", valorGraficaElemento=" + valorGraficaElemento + '}';
    }
    
}
