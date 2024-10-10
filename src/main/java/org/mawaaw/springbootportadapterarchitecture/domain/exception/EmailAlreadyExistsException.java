package org.mawaaw.springbootportadapterarchitecture.domain.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);}
}