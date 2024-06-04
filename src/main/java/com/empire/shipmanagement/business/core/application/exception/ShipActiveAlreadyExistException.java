package com.empire.shipmanagement.business.core.application.exception;


public class ShipActiveAlreadyExistException extends RuntimeException {

    public ShipActiveAlreadyExistException(Long id) {
        super(String.format("The current Ship with name: %s already exist.", id));
    }
}
