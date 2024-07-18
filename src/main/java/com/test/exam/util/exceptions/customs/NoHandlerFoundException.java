package com.test.exam.util.exceptions.customs;

public class NoHandlerFoundException extends RuntimeException {

    private NoHandlerFoundException() {
        super();
    }

    public NoHandlerFoundException(final String message) {
        super(message);
    }
}
