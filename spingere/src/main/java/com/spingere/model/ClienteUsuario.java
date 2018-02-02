package com.spingere.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ClienteUsuario")
@IdClass(ClienteUsuarioPK.class)
public class ClienteUsuario implements Serializable {

    private static final long serialVersionUID = -7819074668506931148L;

    @Id
    @Column
    private Integer idCliente;

    @Id
    @Column
    private Integer idUsuario;

    @Id
    @Column
    private Integer idRolUsuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idRolUsuario", referencedColumnName = "idRolUsuario", insertable = false, updatable = false)
    private RolUsuario rolUsuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    private Usuario usuario;

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRolUsuario() {
        return this.idRolUsuario;
    }

    public void setIdRolUsuario(Integer idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public RolUsuario getRolUsuario() {
        return this.rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ClienteUsuario{" + "idCliente=" + idCliente + ", idUsuario=" + idUsuario + ", idRolUsuario=" + idRolUsuario + ", cliente=" + cliente + ", rolUsuario=" + rolUsuario + ", usuario=" + usuario + '}';
    }

}