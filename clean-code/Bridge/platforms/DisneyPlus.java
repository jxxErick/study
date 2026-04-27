package br.com.hcode.bridge.platforms;

public class DisneyPlus implements IPlatform {

    public DisneyPlus(){
        configureRMTP();
        System.out.println("Disney: Transmissão Iniciada");
    }

    @Override
    public void configureRMTP() {
        authToken();
        System.out.println("Disney: Conta autorizada");
    }

    @Override
    public void authToken() {
        System.out.println("Disney: Conta autorizada");
    }
}
