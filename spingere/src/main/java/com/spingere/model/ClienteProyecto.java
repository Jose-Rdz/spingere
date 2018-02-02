package com.spingere.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author AnGeL
 */
@Entity
@Table(name = "ClienteProyecto")
@IdClass(ClienteProyectoPK.class)
public class ClienteProyecto implements Serializable {

    private static final long serialVersionUID = -5887860575211576295L;
    
    @Id
    @Column
    private Integer idCliente;
    
    @Id
    @Column
    private Integer idProyecto;
    
    @Id
    @Column
    private Integer idProveedor;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public String toString() {
        return "ClienteProyecto{" + "idCliente=" + idCliente + ", idProyecto=" + idProyecto + ", idProveedor=" + idProveedor + '}';
    }
    
}
