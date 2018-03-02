package com.spingere.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jose-Rdz
 */
@Entity
@Table(name = "SubtipoMetrica")
@IdClass(SubtipoMetricaPK.class)
public class SubtipoMetrica implements Serializable {

    private static final long serialVersionUID = 6413912266993462644L;
    
    @Id
    @Column
    private Integer idSubtipoMetrica;
    
    @Id
    @Column
    private Integer idTipoMetrica;
    
    @Column
    private String subtipoMetrica;

    @Column
    private String nmonicoMetrica;

    @ManyToOne
    @JoinColumn(name = "idTipoMetrica", referencedColumnName = "idTipoMetrica", insertable = false, updatable = false)
    private TipoMetrica tipoMetrica;

    public Integer getIdSubtipoMetrica() {
        return idSubtipoMetrica;
    }

    public void setIdSubtipoMetrica(Integer idSubtipoMetrica) {
        this.idSubtipoMetrica = idSubtipoMetrica;
    }

    public Integer getIdTipoMetrica() {
        return idTipoMetrica;
    }

    public void setIdTipoMetrica(Integer idTipoMetrica) {
        this.idTipoMetrica = idTipoMetrica;
    }

    public String getSubtipoMetrica() {
        return subtipoMetrica;
    }

    public void setSubtipoMetrica(String subtipoMetrica) {
        this.subtipoMetrica = subtipoMetrica;
    }

    public String getNmonicoMetrica() {
        return nmonicoMetrica;
    }

    public void setNmonicoMetrica(String nmonicoMetrica) {
        this.nmonicoMetrica = nmonicoMetrica;
    }

    public TipoMetrica getTipoMetrica() {
        return tipoMetrica;
    }

    public void setTipoMetrica(TipoMetrica tipoMetrica) {
        this.tipoMetrica = tipoMetrica;
    }

    @Override
    public String toString() {
        return "SubtipoMetrica{" + "idSubtipoMetrica=" + idSubtipoMetrica + ", idTipoMetrica=" + idTipoMetrica + ", subtipoMetrica=" + subtipoMetrica + ", nmonicoMetrica=" + nmonicoMetrica + ", tipoMetrica=" + tipoMetrica + '}';
    }

}
