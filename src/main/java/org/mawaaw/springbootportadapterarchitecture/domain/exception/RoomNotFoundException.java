package org.mawaaw.springbootportadapterarchitecture.domain.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String message) {
        super(message);
    }
}
