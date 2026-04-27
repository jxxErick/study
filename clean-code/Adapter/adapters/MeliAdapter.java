package br.com.hcode.adapter.adapters;

import br.com.hcode.adapter.meli.Meli;
import br.com.hcode.adapter.paypal.IPayPalPayments;
import br.com.hcode.adapter.utils.Token;

public class MeliAdapter implements IPayPalPayments {
    private Meli meli;

    public MeliAdapter(Meli meli) {
        this.meli = meli;
        System.out.println("MeliAdapter");
    }

    @Override
    public Token authToken() {
        return meli.authToken();
    }

    @Override
    public void paypalPayment() {
        meli.sendPayment();
    }

    @Override
    public void paypalReceive() {
        meli.receivePayment();
    }
}
