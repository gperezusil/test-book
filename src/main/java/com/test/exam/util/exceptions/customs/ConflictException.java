package com.test.exam.util.exceptions.customs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    private ConflictException() {
        super();
    }

    public ConflictException(final String message) {
        super(message);
    }
}
