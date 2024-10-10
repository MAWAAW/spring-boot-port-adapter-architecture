package org.mawaaw.springbootportadapterarchitecture.domain.exception;

import org.springframework.dao.DataAccessException;

public class ClientSaveException extends RuntimeException {
    public ClientSaveException(String message, DataAccessException ex) {
        super(message);}
}
