package com.test.exam.util.exceptions.customs;

public class ResourceNotFoundException extends RuntimeException{

    private ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
