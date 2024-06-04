package com.empire.shipmanagement.business.core.application.exception;

public class ShipAlreadyCurrentStatusException extends ShipUpdateException {

    public ShipAlreadyCurrentStatusException(Long shipId) {
        super(String.format("The current status of the Ship: %s is already the same.", shipId));
    }
}
