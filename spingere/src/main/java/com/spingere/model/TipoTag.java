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
@Table(name = "TipoTag")
public class TipoTag implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoTag;
    
    @Column
    private String tipoTag;

    public Integer getIdTipoTag() {
        return idTipoTag;
    }

    public void setIdTipoTag(Integer idTipoTag) {
        this.idTipoTag = idTipoTag;
    }

    public String getTipoTag() {
        return tipoTag;
    }

    public void setTipoTag(String tipoTag) {
        this.tipoTag = tipoTag;
    }

    @Override
    public String toString() {
        return "TipoTag{" + "idTipoTag=" + idTipoTag + ", tipoTag=" + tipoTag + '}';
    }
    
}
