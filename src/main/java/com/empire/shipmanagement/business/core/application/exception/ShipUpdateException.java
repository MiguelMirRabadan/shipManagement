package com.empire.shipmanagement.business.core.application.exception;


public class ShipUpdateException extends Exception {

    public ShipUpdateException(String cause) {
        super("UPDATE Error:" + cause);
    }
}
