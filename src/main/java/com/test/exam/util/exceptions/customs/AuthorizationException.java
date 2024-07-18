package com.test.exam.util.exceptions.customs;


public class AuthorizationException extends RuntimeException{


    private AuthorizationException() {
        super();
    }

    public AuthorizationException(final String message) {
        super(message);
    }


}
