package com.empire.shipmanagement.business.core.domain.port.io;


import com.empire.shipmanagement.business.core.domain.model.Ship;

public interface ShipDomainManagementPort {

    Ship createShip(Ship newShip);

    Ship updateShip(Ship ship);

    void deleteShipByShipId(Long shipId);

    Ship retrieveShipByShipId(Long shipId);

    Ship modifyShipStatus(Long shipId, String status);

    Ship modifyShipType(Long shipId, String type);


}
