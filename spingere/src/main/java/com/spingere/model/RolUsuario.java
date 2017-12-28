/**
 * This file was generated by the Jeddict
 */
package com.spingere.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rolusuario")
public class RolUsuario implements Serializable {

    private static final long serialVersionUID = -8391647926784291512L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRolUsuario;

    @Column
    private String rolUsuario;

    @Column
    private String descripcionRolUsuario;

    /*@OneToMany(mappedBy = "rolUsuario")
    private List<ClienteUsuario> clientesUsuarios;*/

    public Integer getIdRolUsuario() {
        return this.idRolUsuario;
    }

    public void setIdRolUsuario(Integer idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public String getRolUsuario() {
        return this.rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public String getDescripcionRolUsuario() {
        return this.descripcionRolUsuario;
    }

    public void setDescripcionRolUsuario(String descripcionRolUsuario) {
        this.descripcionRolUsuario = descripcionRolUsuario;
    }

    /*public List<ClienteUsuario> getClientesUsuarios() {
        return this.clientesUsuarios;
    }

    public void setClientesUsuarios(List<ClienteUsuario> clientesUsuarios) {
        this.clientesUsuarios = clientesUsuarios;
    }*/

    @Override
    public String toString() {
        return "RolUsuario{" + "idRolUsuario=" + idRolUsuario + ", rolUsuario=" + rolUsuario + ", descripcionRolUsuario=" + descripcionRolUsuario + '}';
    }
    

}