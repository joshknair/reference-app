package com.nairs.referenceapp.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String error) {
        super(error);
    }
    public NotFoundException(String error, Throwable cause) {
        super(error, cause);
    }
}
