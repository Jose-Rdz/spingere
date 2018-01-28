package com.spingere.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Jose-Rdz
 */
public class GraficaElementoMetricaPK implements Serializable {
    
    private static final long serialVersionUID = -2955680514034545402L;
    
    private Integer idGrafica;
    private Integer idGraficaElemento;
    private Integer idTipoMetrica;
    private Integer idSubtipoMetrica;

    public Integer getIdGrafica() {
        return idGrafica;
    }

    public Integer getIdGraficaElemento() {
        return idGraficaElemento;
    }

    public Integer getIdTipoMetrica() {
        return idTipoMetrica;
    }

    public Integer getIdSubtipoMetrica() {
        return idSubtipoMetrica;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.idGrafica);
        hash = 23 * hash + Objects.hashCode(this.idGraficaElemento);
        hash = 23 * hash + Objects.hashCode(this.idTipoMetrica);
        hash = 23 * hash + Objects.hashCode(this.idSubtipoMetrica);
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
        final GraficaElementoMetricaPK other = (GraficaElementoMetricaPK) obj;
        if (!Objects.equals(this.idGrafica, other.idGrafica)) {
            return false;
        }
        if (!Objects.equals(this.idGraficaElemento, other.idGraficaElemento)) {
            return false;
        }
        if (!Objects.equals(this.idTipoMetrica, other.idTipoMetrica)) {
            return false;
        }
        if (!Objects.equals(this.idSubtipoMetrica, other.idSubtipoMetrica)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GraficaElementoMetricaPK{" + "idGrafica=" + idGrafica + ", idGraficaElemento=" + idGraficaElemento + ", idTipoMetrica=" + idTipoMetrica + ", idSubtipoMetrica=" + idSubtipoMetrica + '}';
    }
    
}
