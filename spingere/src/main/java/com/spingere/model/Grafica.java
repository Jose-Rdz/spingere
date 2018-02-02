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
 * @author Jose-Rdz
 */
@Entity
@Table(name = "Grafica")
public class Grafica implements Serializable {

    private static final long serialVersionUID = -2844680721178538895L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrafica;
    
    @Column
    private String nombreGrafica;

    public Integer getIdGrafica() {
        return idGrafica;
    }

    public void setIdGrafica(Integer idGrafica) {
        this.idGrafica = idGrafica;
    }

    public String getNombreGrafica() {
        return nombreGrafica;
    }

    public void setNombreGrafica(String nombreGrafica) {
        this.nombreGrafica = nombreGrafica;
    }

    @Override
    public String toString() {
        return "Grafica{" + "idGrafica=" + idGrafica + ", nombreGrafica=" + nombreGrafica + '}';
    }
    
}
