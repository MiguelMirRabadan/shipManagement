package com.empire.shipmanagement.infraestructure.adapter.in.rest.controller;

import com.empire.shipmanagement.business.core.application.dto.SearchResultDto;
import com.empire.shipmanagement.business.core.application.dto.ShipFilter;
import com.empire.shipmanagement.business.core.application.exception.ShipNotExistException;
import com.empire.shipmanagement.business.core.application.port.in.ShipBusinessManagementPort;
import com.empire.shipmanagement.business.core.domain.model.Ship;
import com.empire.shipmanagement.business.core.domain.model.ShipStatus;
import com.empire.shipmanagement.business.core.domain.model.ShipType;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.api.ShipApi;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.FullShipInput;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.ShipInput;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.ShipInputFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.empire.shipmanagement.infraestructure.adapter.config.CacheConfig.SHIP_INFO_CACHE;

@RestController
@Log4j2
public class ShipController implements ShipApi {

    @Autowired
    ShipBusinessManagementPort shipBusinessManagementPort;

    /**
     * Create a new Ship
     *
     * @param shipInput: Ship to create
     */
    @Override
    public ResponseEntity<Void> newShip(ShipInput shipInput) {
        log.info("Creating new Ship with name: {}", shipInput.getId());
        shipBusinessManagementPort.createShip(ShipInputFactory.inputToModel(shipInput));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public Ship updateShip(FullShipInput fullShipInput, Long id) {
        log.info("Updating Ship with id: {}", id);
        return Optional.of(Objects.equals(id, fullShipInput.getId()))
                .filter(Boolean::booleanValue)
                .map(areEqual -> this.shipBusinessManagementPort.updateShip(ShipInputFactory.inputToModel(fullShipInput)))
                .orElseThrow(() -> new ShipNotExistException(fullShipInput.getId()));
    }

    @Override
    public Ship getShipById(Long id) {
        log.info("Retrieve Ship with id: {}", id);
        return this.shipBusinessManagementPort.retrieveShipByShipId(id);
    }

    @Override
    public void deleteShip(Long id) {
        log.info("Delete Ship with id: {}", id);
        this.shipBusinessManagementPort.deleteShipByShipId(id);
    }

    @Override
    public SearchResultDto findShips(Optional<Long> id, Optional<String> name, Optional<ShipType> type,
                                     Optional<ShipStatus> status, Optional<String> description, Optional<Set<Long>> films,
                                     Integer pageIndex, Integer pageSize, Optional<String> sortField,
                                     Optional<Sort.Direction> sortDirection) {
        log.info("Search Ships by params...");
        return this.shipBusinessManagementPort.getShipSearchResult(ShipFilter.builder()
                .id(id)
                .name(name)
                .type(type)
                .status(status)
                .description(description)
                .films(films)
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .sortDirection(sortDirection)
                .sortField(sortField).build());
    }
}
