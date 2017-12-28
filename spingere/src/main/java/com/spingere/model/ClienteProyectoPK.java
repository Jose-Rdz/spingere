package com.spingere.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author AnGeL
 */
public class ClienteProyectoPK implements Serializable {

    private static final long serialVersionUID = 9006068420000068146L;

    private Integer idCliente;
    private Integer idProyecto;
    private Integer idProveedor;

    public Integer getIdCliente() {
        return idCliente;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.idCliente);
        hash = 37 * hash + Objects.hashCode(this.idProyecto);
        hash = 37 * hash + Objects.hashCode(this.idProveedor);
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
        final ClienteProyectoPK other = (ClienteProyectoPK) obj;
        if (!Objects.equals(this.idCliente, other.idCliente)) {
            return false;
        }
        if (!Objects.equals(this.idProyecto, other.idProyecto)) {
            return false;
        }
        if (!Objects.equals(this.idProveedor, other.idProveedor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClienteProyectoPK{" + "idCliente=" + idCliente + ", idProyecto=" + idProyecto + ", idProveedor=" + idProveedor + '}';
    }
    
}
