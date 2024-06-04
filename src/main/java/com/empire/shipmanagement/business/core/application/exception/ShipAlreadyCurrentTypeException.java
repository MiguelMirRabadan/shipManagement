package com.empire.shipmanagement.business.core.application.exception;

public class ShipAlreadyCurrentTypeException extends ShipUpdateException {

    public ShipAlreadyCurrentTypeException(Long shipId) {
        super(String.format("The current type of the Ship: %s is already the same.", shipId));
    }
}
