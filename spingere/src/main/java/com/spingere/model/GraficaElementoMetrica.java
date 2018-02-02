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
@Table(name = "GraficaElementoMetrica")
@IdClass(GraficaElementoMetricaPK.class)
public class GraficaElementoMetrica implements Serializable {
    
    @Id
    @Column
    private Integer idGrafica;
    
    @Id
    @Column
    private Integer idGraficaElemento;
    
    @Id
    @Column
    private Integer idTipoMetrica;
    
    @Id
    @Column
    private Integer idSubtipoMetrica;
    
    @Column
    private String formatoElementoMetrica;

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

    public Integer getIdTipoMetrica() {
        return idTipoMetrica;
    }

    public void setIdTipoMetrica(Integer idTipoMetrica) {
        this.idTipoMetrica = idTipoMetrica;
    }

    public Integer getIdSubtipoMetrica() {
        return idSubtipoMetrica;
    }

    public void setIdSubtipoMetrica(Integer idSubtipoMetrica) {
        this.idSubtipoMetrica = idSubtipoMetrica;
    }

    public String getFormatoElementoMetrica() {
        return formatoElementoMetrica;
    }

    public void setFormatoElementoMetrica(String formatoElementoMetrica) {
        this.formatoElementoMetrica = formatoElementoMetrica;
    }

    @Override
    public String toString() {
        return "GraficaElementoMetrica{" + "idGrafica=" + idGrafica + ", idGraficaElemento=" + idGraficaElemento + ", idTipoMetrica=" + idTipoMetrica + ", idSubtipoMetrica=" + idSubtipoMetrica + ", formatoElementoMetrica=" + formatoElementoMetrica + '}';
    }
    
}
