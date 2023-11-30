package com.api.users.application.exceptions;

public class InvalidPasswordException extends Exception {
    private static final String DEFAULT_MESSAGE = "El password no cumple con los parametros de seguridad";

    public InvalidPasswordException() {
        super(DEFAULT_MESSAGE);
    }
}
