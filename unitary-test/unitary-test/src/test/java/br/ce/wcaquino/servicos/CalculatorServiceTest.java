package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;


public class CalculatorServiceTest {


    CalculatorService service;
    BigDecimal n1;
    BigDecimal n2;
    BigDecimal result;

    @Before
    public void setUp(){
        service = new CalculatorService();
        n1=new BigDecimal("1");
        n2=new BigDecimal("2");
    }

    @Test
    public void test_calculatorSum() {

        result = service.sum(n1, n2);

        Assert.assertNotNull(result);
        Assert.assertEquals(new BigDecimal("3"), result);

    }
    @Test
    public void test_calculatorSub() {
        result = service.sub(n1, n2);

        Assert.assertNotNull(result);
        Assert.assertEquals(new BigDecimal("-1"), result);
    }

    @Test
    public void test_calculatorMul() {
        result = service.multiply(n1, n2);
        Assert.assertNotNull(result);
        Assert.assertEquals(new BigDecimal("2"), result);
    }


    @Test
    public void test_calculatorDiv() {
        result = service.divide(n1, n2);
        Assert.assertNotNull(result);
        Assert.assertEquals(new BigDecimal("0.5"), result);
    }
}
