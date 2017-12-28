package com.spingere.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author AnGeL
 */
@Entity
@Table(name = "proveedor")
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 2841586404136521771L;
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProveedor;
    
    @Column
    private String proveedor;
    
    @Column
    private String razonSocialProveedor;
    
    @Column
    private String personaContactProvee1;
    
    @Column
    private String correoContactProvee1;
    
    @Column
    private String personaContactProvee2;
    
    @Column
    private String correoContactProvee2;

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
    }

    public String getPersonaContactProvee1() {
        return personaContactProvee1;
    }

    public void setPersonaContactProvee1(String personaContactProvee1) {
        this.personaContactProvee1 = personaContactProvee1;
    }

    public String getCorreoContactProvee1() {
        return correoContactProvee1;
    }

    public void setCorreoContactProvee1(String correoContactProvee1) {
        this.correoContactProvee1 = correoContactProvee1;
    }

    public String getPersonaContactProvee2() {
        return personaContactProvee2;
    }

    public void setPersonaContactProvee2(String personaContactProvee2) {
        this.personaContactProvee2 = personaContactProvee2;
    }

    public String getCorreoContactProvee2() {
        return correoContactProvee2;
    }

    public void setCorreoContactProvee2(String correoContactProvee2) {
        this.correoContactProvee2 = correoContactProvee2;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "idProveedor=" + idProveedor + ", proveedor=" + proveedor + ", razonSocialProveedor=" + razonSocialProveedor + ", personaContactProvee1=" + personaContactProvee1 + ", correoContactProvee1=" + correoContactProvee1 + ", personaContactProvee2=" + personaContactProvee2 + ", correoContactProvee2=" + correoContactProvee2 + '}';
    }
    
}
