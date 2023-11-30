package com.api.users.application.exceptions;

public class EmailAlreadyRegisteredException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "El correo ya fue registrado";

    public EmailAlreadyRegisteredException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
