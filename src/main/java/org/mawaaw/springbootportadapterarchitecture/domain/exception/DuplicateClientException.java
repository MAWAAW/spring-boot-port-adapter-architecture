package org.mawaaw.springbootportadapterarchitecture.domain.exception;

public class DuplicateClientException extends RuntimeException {
    public DuplicateClientException(String message) {
        super(message);
    }
}
