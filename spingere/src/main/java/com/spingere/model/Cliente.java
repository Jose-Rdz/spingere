package com.spingere.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = -7827456836744806751L;

    @Id
    @Column
    private Integer idCliente;

    @Column
    private String cliente;

    @Column
    private String razonSocialCliente;
    
    @ManyToMany
    @JoinTable(name = "ClienteProyecto", 
            joinColumns = @JoinColumn(name = "idCliente", referencedColumnName = "idCliente"),
            inverseJoinColumns = @JoinColumn(name = "idProyecto", referencedColumnName = "idProyecto"))
    private List<Proyecto> proyectos;

    public Cliente() {
    }

    public Cliente(Integer idCliente, String cliente, String razonSocialCliente) {
        this.idCliente = idCliente;
        this.cliente = cliente;
        this.razonSocialCliente = razonSocialCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getRazonSocialCliente() {
        return razonSocialCliente;
    }

    public void setRazonSocialCliente(String razonSocialCliente) {
        this.razonSocialCliente = razonSocialCliente;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", cliente=" + cliente + ", razonSocialCliente=" + razonSocialCliente + ", proyectos=" + proyectos + '}';
    }

}