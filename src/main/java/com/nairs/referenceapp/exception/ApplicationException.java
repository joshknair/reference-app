package com.nairs.referenceapp.exception;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String error) {
        super(error);
    }
    public ApplicationException(String error, Throwable cause) {
        super(error, cause);
    }
}
