package com.meli.tetera.exceptions;

public class SystemNotFoundException extends RuntimeException {
    public SystemNotFoundException(String message) {
        super(message);
    }
}
