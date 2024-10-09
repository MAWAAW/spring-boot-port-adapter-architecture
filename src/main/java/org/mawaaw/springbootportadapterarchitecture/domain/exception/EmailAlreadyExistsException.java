package org.mawaaw.springbootportadapterarchitecture.domain.exception;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String message) {
        super(message);}
}