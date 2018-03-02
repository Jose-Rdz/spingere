package com.spingere.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Jose-Rdz
 */
public class ModeloDto implements Serializable {

    private static final long serialVersionUID = 8100516225234384885L;
    
    private Integer numIntervalo;
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal limiteSuperior;
    private BigDecimal limiteInferior;

    public Integer getNumIntervalo() {
        return numIntervalo;
    }

    public void setNumIntervalo(Integer numIntervalo) {
        this.numIntervalo = numIntervalo;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(BigDecimal limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public BigDecimal getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(BigDecimal limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    @Override
    public String toString() {
        return "ModeloDto{" + "numIntervalo=" + numIntervalo + ", x=" + x + ", y=" + y + ", limiteSuperior=" + limiteSuperior + ", limiteInferior=" + limiteInferior + '}';
    }
    
}
