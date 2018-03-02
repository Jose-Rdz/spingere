package com.spingere.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Jose-Rdz
 */
public class GraficaBarrasDto implements Serializable {

    private static final long serialVersionUID = 63121840557641219L;
    
    private String nombre;
    private BigDecimal valor;

    public GraficaBarrasDto() {
    }

    public GraficaBarrasDto(String nombre, BigDecimal valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "GraficaBarrasDto{" + "nombre=" + nombre + ", valor=" + valor + '}';
    }
    
}
