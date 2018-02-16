package com.spingere.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Jose-Rdz
 */
public class GraficaElementoValorPK implements Serializable {

    private static final long serialVersionUID = -5583151153522768181L;
    
    private Integer idGrafica;
    private Integer idGraficaElemento;

    public Integer getIdGrafica() {
        return idGrafica;
    }

    public Integer getIdGraficaElemento() {
        return idGraficaElemento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idGrafica);
        hash = 67 * hash + Objects.hashCode(this.idGraficaElemento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GraficaElementoValorPK other = (GraficaElementoValorPK) obj;
        if (!Objects.equals(this.idGrafica, other.idGrafica)) {
            return false;
        }
        if (!Objects.equals(this.idGraficaElemento, other.idGraficaElemento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GraficaElementoValorPK{" + "idGrafica=" + idGrafica + ", idGraficaElemento=" + idGraficaElemento + '}';
    }
    
}
