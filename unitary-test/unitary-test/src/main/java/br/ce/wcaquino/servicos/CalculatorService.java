package br.ce.wcaquino.servicos;

import java.math.BigDecimal;

public class CalculatorService {

    public BigDecimal sum(BigDecimal valor1, BigDecimal valor2) {
        return valor1.add(valor2);
    }

    public BigDecimal sub(BigDecimal valor1, BigDecimal valor2) {
        return valor1.subtract(valor2);
    }

    public BigDecimal multiply(BigDecimal valor1, BigDecimal valor2) {
        return valor1.multiply(valor2);
    }

    public BigDecimal divide(BigDecimal valor1, BigDecimal valor2) {
        return valor1.divide(valor2);
    }


}
