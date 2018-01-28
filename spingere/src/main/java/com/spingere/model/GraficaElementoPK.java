package com.spingere.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Jose-Rdz
 */
public class GraficaElementoPK implements Serializable {

    private static final long serialVersionUID = -6727850644941811970L;
    
    private Integer idGraficaElemento;
    private Integer idGrafica;

    public Integer getIdGraficaElemento() {
        return idGraficaElemento;
    }

    public Integer getIdGrafica() {
        return idGrafica;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idGraficaElemento);
        hash = 71 * hash + Objects.hashCode(this.idGrafica);
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
        final GraficaElementoPK other = (GraficaElementoPK) obj;
        if (!Objects.equals(this.idGraficaElemento, other.idGraficaElemento)) {
            return false;
        }
        if (!Objects.equals(this.idGrafica, other.idGrafica)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GraficaElementoPK{" + "idGraficaElemento=" + idGraficaElemento + ", idGrafica=" + idGrafica + '}';
    }
    
}
