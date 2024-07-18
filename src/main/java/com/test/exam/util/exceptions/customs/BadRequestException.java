package com.test.exam.util.exceptions.customs;

public class BadRequestException extends RuntimeException {

    private BadRequestException() {
        super();
    }

    public BadRequestException(final String message) {
        super(message);
    }
}
