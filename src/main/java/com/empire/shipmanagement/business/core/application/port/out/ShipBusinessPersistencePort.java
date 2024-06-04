package com.empire.shipmanagement.business.core.application.port.out;


import com.empire.shipmanagement.business.core.application.dto.ShipFilter;
import com.empire.shipmanagement.business.core.domain.model.Ship;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ShipBusinessPersistencePort {
    Ship createShip(Ship newShip);
    Ship updateShip(Ship ship);
    void deleteShipById(Long shipId);
    Optional<Ship> getShipById(Long shipId);
    boolean shipExist(Long shipId);
    Page<Ship> findShips(ShipFilter shipFilter);
}
