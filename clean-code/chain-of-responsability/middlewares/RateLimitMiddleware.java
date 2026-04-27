package br.com.hcode.chainofresponsibility.middlewares;

public class RateLimitMiddleware extends Middleware {

    private int requestPerMinute;
    private int request;
    private long currentTime;

    public RateLimitMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    @Override
    public boolean check(String email, String password) {

        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;

        if (request > requestPerMinute) {
            System.out.println("Muitas tentativas! Tente novamente mais tarde.");
            return false;
        }

        return checkNext(email, password);
    }
}