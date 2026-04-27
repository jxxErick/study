package br.com.hcode.adapter;

import br.com.hcode.adapter.adapters.PayoneerAdapter;
import br.com.hcode.adapter.payoneer.IPayonnerPayments;
import br.com.hcode.adapter.payoneer.Payoneer;
import br.com.hcode.adapter.paypal.IPayPalPayments;
import br.com.hcode.adapter.paypal.PayPal;

public class Main {

    public static void main(String[] args) {

        //IPayonnerPayments payment = new Payoneer();



        IPayPalPayments payment = new PayoneerAdapter(new Payoneer());

        payment.paypalPayment();
        payment.paypalReceive();


    }
}
