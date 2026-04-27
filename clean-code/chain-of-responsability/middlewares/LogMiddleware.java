package br.com.hcode.chainofresponsibility.middlewares;

public class LogMiddleware extends Middleware {

    @Override
    public boolean check(String email, String password) {
        System.out.println("Tentativa de login para: " + email);
        return checkNext(email, password);
    }
}