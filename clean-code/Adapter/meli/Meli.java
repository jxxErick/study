package br.com.hcode.adapter.meli;

import br.com.hcode.adapter.utils.Token;

public class Meli implements IMeliPayments {

    @Override
    public Token authToken() {
        return new Token();
    }

    @Override
    public void sendPayment() {
        System.out.println("Meli sendPayment");
    }
    @Override
    public void receivePayment() {
        System.out.println("Meli receivePayment");
    }
}
