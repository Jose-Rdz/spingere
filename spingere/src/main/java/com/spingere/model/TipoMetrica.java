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
@Table(name = "tipometrica")
public class TipoMetrica implements Serializable {

    private static final long serialVersionUID = 8304915053997476893L;
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoMetrica;
    
    @Column
    private String tipoMetrica;

    public Integer getIdTipoMetrica() {
        return idTipoMetrica;
    }

    public void setIdTipoMetrica(Integer idTipoMetrica) {
        this.idTipoMetrica = idTipoMetrica;
    }

    public String getTipoMetrica() {
        return tipoMetrica;
    }

    public void setTipoMetrica(String tipoMetrica) {
        this.tipoMetrica = tipoMetrica;
    }

    @Override
    public String toString() {
        return "TipoMetrica{" + "idTipoMetrica=" + idTipoMetrica + ", tipoMetrica=" + tipoMetrica + '}';
    }
    
}