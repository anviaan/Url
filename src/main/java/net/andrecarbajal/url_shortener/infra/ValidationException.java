package net.andrecarbajal.url_shortener.infra;

public class ValidationException extends RuntimeException {
    public ValidationException(String s) {
        super(s);
    }
}
