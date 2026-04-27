package br.com.hcode.adapter.meli;

import br.com.hcode.adapter.utils.Token;

public interface IMeliPayments {
    Token authToken();
    void sendPayment();
    void receivePayment();
}
