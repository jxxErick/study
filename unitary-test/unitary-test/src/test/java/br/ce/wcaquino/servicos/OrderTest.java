package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

public class OrderTest {

    // the test not using a specify order

    public static int count = 0;

    @Test
    public  void init(){
        count++;
    }

    @Test
    public void verify(){
        Assert.assertEquals(1, count);
    }


}
