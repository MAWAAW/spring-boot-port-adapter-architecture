package org.mawaaw.springbootportadapterarchitecture.domain.exception;

import org.springframework.dao.DataAccessException;

public class ClientSaveException extends Exception {
    public ClientSaveException(String message, DataAccessException ex) {
        super(message);}
}
