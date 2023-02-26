package com.example.grouptransportapi.handler;

public class ListEmptyException extends RuntimeException {
    public ListEmptyException(String message) {
        super(message);
    }
    public ListEmptyException(Throwable cause) {
        super(cause);
    }
}

