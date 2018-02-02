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
@Table(name = "Proyecto")
public class Proyecto implements Serializable {

    private static final long serialVersionUID = -1045944191890006765L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProyecto;
    
    @Column
    private String proyecto;
    
    @Column
    private String descripcionProyecto;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto, String proyecto, String descripcionProyecto) {
        this.idProyecto = idProyecto;
        this.proyecto = proyecto;
        this.descripcionProyecto = descripcionProyecto;
    }
    
    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "idProyecto=" + idProyecto + ", proyecto=" + proyecto + ", descripcionProyecto=" + descripcionProyecto + '}';
    }
    
}
