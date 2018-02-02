package com.spingere.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author AnGeL
 */
@Entity
@Table(name = "Metrica")
@IdClass(MetricaPK.class)
public class Metrica implements Serializable {

    private static final long serialVersionUID = 8456277194760321312L;
    
    @Id
    @Column
    private Integer idProyecto;
    
    @Id
    @Column
    private Integer idTipoMetrica;
    
    @Id
    @Column
    private Integer idSubtipoMetrica;
    
    @Column
    private String valorMetrica;
    
    @Column
    private Integer valorEnteroMetrica;
    
    @Column
    private BigDecimal valorDecimalMetrica;
    
    @Column
    private LocalDateTime fechaMetrica;

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdTipoMetrica() {
        return idTipoMetrica;
    }

    public void setIdTipoMetrica(Integer idTipoMetrica) {
        this.idTipoMetrica = idTipoMetrica;
    }

    public Integer getIdSubtipoMetrica() {
        return idSubtipoMetrica;
    }

    public void setIdSubtipoMetrica(Integer idSubtipoMetrica) {
        this.idSubtipoMetrica = idSubtipoMetrica;
    }

    public String getValorMetrica() {
        return valorMetrica;
    }

    public void setValorMetrica(String valorMetrica) {
        this.valorMetrica = valorMetrica;
    }

    public Integer getValorEnteroMetrica() {
        return valorEnteroMetrica;
    }

    public void setValorEnteroMetrica(Integer valorEnteroMetrica) {
        this.valorEnteroMetrica = valorEnteroMetrica;
    }

    public BigDecimal getValorDecimalMetrica() {
        return valorDecimalMetrica;
    }

    public void setValorDecimalMetrica(BigDecimal valorDecimalMetrica) {
        this.valorDecimalMetrica = valorDecimalMetrica;
    }

    public LocalDateTime getFechaMetrica() {
        return fechaMetrica;
    }

    public void setFechaMetrica(LocalDateTime fechaMetrica) {
        this.fechaMetrica = fechaMetrica;
    }
    
    @Override
    public String toString() {
        return "Metrica{" + "idProyecto=" + idProyecto + ", idTipoMetrica=" + idTipoMetrica + ", idSubtipoMetrica=" + idSubtipoMetrica + ", valorMetrica=" + valorMetrica + ", valorEnteroMetrica=" + valorEnteroMetrica + ", valorDecimalMetrica=" + valorDecimalMetrica + ", fechaMetrica=" + fechaMetrica + '}';
    }

}
