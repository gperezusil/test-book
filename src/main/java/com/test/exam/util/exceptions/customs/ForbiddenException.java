package com.test.exam.util.exceptions.customs;

public class ForbiddenException extends  RuntimeException{

    private ForbiddenException() {
        super();
    }

    public ForbiddenException(final String message) {
        super(message);
    }
}
