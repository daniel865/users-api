package com.api.users.application.exceptions;

public class InvalidEmailException extends Exception {
    private static final String DEFAULT_MESSAGE = "El correo no tiene un formato valido";

    public InvalidEmailException() {
        super(DEFAULT_MESSAGE);
    }
}
