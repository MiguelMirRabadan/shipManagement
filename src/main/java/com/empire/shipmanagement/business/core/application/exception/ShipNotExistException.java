package com.empire.shipmanagement.business.core.application.exception;


public class ShipNotExistException extends RuntimeException {

    public ShipNotExistException(Long shipId) {
        super(String.format("The Ship with ID: %s not exist.", shipId));
    }
}
