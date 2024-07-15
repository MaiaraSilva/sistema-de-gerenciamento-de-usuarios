package br.maiarasilva.demo.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(String.format(message));
    }
}
