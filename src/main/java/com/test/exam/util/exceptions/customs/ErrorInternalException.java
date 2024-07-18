package com.test.exam.util.exceptions.customs;

public class ErrorInternalException extends RuntimeException {

    private ErrorInternalException() {
        super();
    }

    public ErrorInternalException(final String message) {
        super(message);
    }
}
