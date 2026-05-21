package br.ce.wcaquino.exception;

public class MovieWithoutStockException extends RuntimeException {
    public MovieWithoutStockException(String message) {
        super(message);
    }
}
