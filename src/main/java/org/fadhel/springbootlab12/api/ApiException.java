package org.fadhel.springbootlab12.api;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
