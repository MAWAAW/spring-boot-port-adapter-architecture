package org.mawaaw.springbootportadapterarchitecture.domain.exception;

public class DuplicateRoomException extends RuntimeException {
    public DuplicateRoomException(String message) {
        super(message);
    }
}
