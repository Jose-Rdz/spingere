package com.spingere.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author AnGeL
 */
public class MetricaPK implements Serializable {

    private static final long serialVersionUID = 4840973123716028639L;
    
    private Integer idProyecto;    
    private Integer idTipoMetrica;
    private Integer idSubtipoMetrica;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdProyecto() {
        return idProyecto;
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
        hash = 37 * hash + Objects.hashCode(this.idProyecto);
        hash = 37 * hash + Objects.hashCode(this.idTipoMetrica);
        hash = 37 * hash + Objects.hashCode(this.idSubtipoMetrica);
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
        final MetricaPK other = (MetricaPK) obj;
        if (!Objects.equals(this.idProyecto, other.idProyecto)) {
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
        return "MetricaPK{" + "idProyecto=" + idProyecto + ", idTipoMetrica=" + idTipoMetrica + ", idSubtipoMetrica=" + idSubtipoMetrica + '}';
    }
    
}
