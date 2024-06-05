package com.empire.shipmanagement.business.core.application.port.in;

import com.empire.shipmanagement.business.core.application.dto.SearchResultDto;
import com.empire.shipmanagement.business.core.application.dto.ShipFilter;
import com.empire.shipmanagement.business.core.application.exception.ShipActiveAlreadyExistException;
import com.empire.shipmanagement.business.core.application.exception.ShipAlreadyCurrentStatusException;
import com.empire.shipmanagement.business.core.application.exception.ShipAlreadyCurrentTypeException;
import com.empire.shipmanagement.business.core.application.exception.ShipNotExistException;
import com.empire.shipmanagement.business.core.application.port.out.ShipBusinessPersistencePort;
import com.empire.shipmanagement.business.core.domain.model.EventType;
import com.empire.shipmanagement.business.core.domain.model.Ship;
import com.empire.shipmanagement.business.core.domain.model.ShipStatus;
import com.empire.shipmanagement.business.core.domain.model.ShipType;
import com.empire.shipmanagement.business.core.domain.port.io.EventPublisher;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.empire.shipmanagement.infraestructure.adapter.config.CacheConfig.SHIP_INFO_CACHE;

@Service
@Log4j2
public class ShipBusinessManagementPortImp implements ShipBusinessManagementPort {

    @Autowired
    private ShipBusinessPersistencePort shipBusinessPersistencePort;
    @Autowired
    private EventPublisher eventPublisher;

    @Override
    @Transactional
    public Ship createShip(Ship newShip) {
        log.debug("");
        if (shipBusinessPersistencePort.shipExist(newShip.getId())) {
            throw new ShipActiveAlreadyExistException(newShip.getId());
        }
        Ship createdShip =  shipBusinessPersistencePort.createShip(newShip.initNewShip());
        eventPublisher.publish(null, EventType.CREATED, createdShip);
        return createdShip;
    }

    @Override
    @Transactional
    public Ship updateShip(Ship ship) {
        if(!shipBusinessPersistencePort.shipExist(ship.getId())){
            throw new ShipNotExistException(ship.getId());
        }
        return shipBusinessPersistencePort.updateShip(ship);
    }

    @Override
    @Transactional
    public void deleteShipByShipId(Long shipId) {
        if (!shipBusinessPersistencePort.shipExist(shipId)) {
            throw new ShipNotExistException(shipId);
        }
        shipBusinessPersistencePort.deleteShipById(shipId);
    }

    @Override
    @Transactional
    public Ship retrieveShipByShipId(Long shipId) {
        return shipBusinessPersistencePort.getShipById(shipId).orElseThrow(() ->
                new ShipNotExistException(shipId));
    }

    @Override
    @SneakyThrows
    @Transactional
    public Ship modifyShipStatus(Long shipId, String status) {
        Ship currentShip = shipBusinessPersistencePort.getShipById(shipId).orElseThrow(() ->
                new ShipNotExistException(shipId));
        if (currentShip.getStatus().toString().equals(status)) {
            throw new ShipAlreadyCurrentStatusException(shipId);
        } else {
            //TODO add other verifications of status exist
            currentShip.setStatus(ShipStatus.valueOf(status));
        }
        return shipBusinessPersistencePort.updateShip(currentShip);
    }

    @Override
    @SneakyThrows
    @Transactional
    public Ship modifyShipType(Long shipId, String type) {
        Ship currentShip = shipBusinessPersistencePort.getShipById(shipId).orElseThrow(() ->
                new ShipNotExistException(shipId));
        if (currentShip.getType().toString().equals(type)) {
            throw new ShipAlreadyCurrentTypeException(shipId);
        } else {
            currentShip.setType(ShipType.valueOf(type));
        }
        return shipBusinessPersistencePort.updateShip(currentShip);
    }

    //TODO: La cache, almacena en caso de error por X motivo causado, el resultado, si intentamos acceder
    //  sin introducir dato que de error, retorna nada, ya que almacenó correctamente el valor vacio.
    @Override
    @Cacheable(value = SHIP_INFO_CACHE, key = "#shipFilter.id != null ? #shipFilter.id : #shipFilter.pageIndex", unless = "#result == null")
    public SearchResultDto getShipSearchResult(ShipFilter shipFilter) {
        return SearchResultDto.toDTO(findShips(shipFilter));
    }
    public Page<Ship> findShips(ShipFilter shipFilter) {
        return shipBusinessPersistencePort.findShips(shipFilter);
    }
}
