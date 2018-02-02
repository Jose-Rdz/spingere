package com.spingere.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author Jose-Rdz
 */
@Entity
@Table(name = "GraficaElemento")
@IdClass(GraficaElementoPK.class)
public class GraficaElemento implements Serializable {

    private static final long serialVersionUID = -4682907245983328402L;
    
    @Id
    @Column
    private Integer idGraficaElemento;
    
    @Id
    @Column
    private Integer idGrafica;
    
    @Column
    private Integer idTipoTag;
    
    @Column
    private Short inicioAgrupacion;
    
    @Column
    private Integer renglonElemento;
    
    @Column
    private Integer ordenElemento;
    
    @Column
    private Integer renglonMultiple;

    public Integer getIdGraficaElemento() {
        return idGraficaElemento;
    }

    public void setIdGraficaElemento(Integer idGraficaElemento) {
        this.idGraficaElemento = idGraficaElemento;
    }

    public Integer getIdGrafica() {
        return idGrafica;
    }

    public void setIdGrafica(Integer idGrafica) {
        this.idGrafica = idGrafica;
    }

    public Integer getIdTipoTag() {
        return idTipoTag;
    }

    public void setIdTipoTag(Integer idTipoTag) {
        this.idTipoTag = idTipoTag;
    }

    public Short getInicioAgrupacion() {
        return inicioAgrupacion;
    }

    public void setInicioAgrupacion(Short inicioAgrupacion) {
        this.inicioAgrupacion = inicioAgrupacion;
    }

    public Integer getRenglonElemento() {
        return renglonElemento;
    }

    public void setRenglonElemento(Integer renglonElemento) {
        this.renglonElemento = renglonElemento;
    }

    public Integer getOrdenElemento() {
        return ordenElemento;
    }

    public void setOrdenElemento(Integer ordenElemento) {
        this.ordenElemento = ordenElemento;
    }

    public Integer getRenglonMultiple() {
        return renglonMultiple;
    }

    public void setRenglonMultiple(Integer renglonMultiple) {
        this.renglonMultiple = renglonMultiple;
    }

    @Override
    public String toString() {
        return "GraficaElemento{" + "idGraficaElemento=" + idGraficaElemento + ", idGrafica=" + idGrafica + ", idTipoTag=" + idTipoTag + ", inicioAgrupacion=" + inicioAgrupacion + ", renglonElemento=" + renglonElemento + ", ordenElemento=" + ordenElemento + ", renglonMultiple=" + renglonMultiple + '}';
    }
    
}
