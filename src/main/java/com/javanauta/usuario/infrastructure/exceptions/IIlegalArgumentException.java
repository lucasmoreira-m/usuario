package com.javanauta.usuario.infrastructure.exceptions;

public class IIlegalArgumentException extends RuntimeException {
    public IIlegalArgumentException(String message) {
        super(message);
    }

    public IIlegalArgumentException(String message, Throwable throwable) {
        super(message, throwable);
    }

}


