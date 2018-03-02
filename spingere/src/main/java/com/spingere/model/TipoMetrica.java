package com.spingere.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jose-Rdz
 */
@Entity
@Table(name = "TipoMetrica")
public class TipoMetrica implements Serializable {

    private static final long serialVersionUID = 8304915053997476893L;
    
    @Id
    @Column
    private Integer idTipoMetrica;
    
    @Column
    private String tipoMetrica;

    public TipoMetrica() {
    }

    public TipoMetrica(Integer idTipoMetrica, String tipoMetrica) {
        this.idTipoMetrica = idTipoMetrica;
        this.tipoMetrica = tipoMetrica;
    }

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
