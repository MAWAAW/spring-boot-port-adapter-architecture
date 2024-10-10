package org.mawaaw.springbootportadapterarchitecture.domain.exception;

public class RoomNotAvailableException extends RuntimeException {
    public RoomNotAvailableException(String message) {
        super(message);
    }
}