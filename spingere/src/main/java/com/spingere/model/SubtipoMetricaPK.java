package com.spingere.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author AnGeL
 */
public class SubtipoMetricaPK implements Serializable {

    private static final long serialVersionUID = -5910630229452856905L;
    
    private Integer idSubtipoMetrica;
    private Integer idTipoMetrica;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdSubtipoMetrica() {
        return idSubtipoMetrica;
    }

    public Integer getIdTipoMetrica() {
        return idTipoMetrica;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idSubtipoMetrica);
        hash = 23 * hash + Objects.hashCode(this.idTipoMetrica);
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
        final SubtipoMetricaPK other = (SubtipoMetricaPK) obj;
        if (!Objects.equals(this.idSubtipoMetrica, other.idSubtipoMetrica)) {
            return false;
        }
        if (!Objects.equals(this.idTipoMetrica, other.idTipoMetrica)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SubtipoMetricaPK{" + "idSubtipoMetrica=" + idSubtipoMetrica + ", idTipoMetrica=" + idTipoMetrica + '}';
    }
    
}
