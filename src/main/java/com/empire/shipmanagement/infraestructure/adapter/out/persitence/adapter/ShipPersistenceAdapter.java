package com.empire.shipmanagement.infraestructure.adapter.out.persitence.adapter;

import com.empire.shipmanagement.business.core.application.dto.ShipFilter;
import com.empire.shipmanagement.business.core.application.port.out.ShipBusinessPersistencePort;
import com.empire.shipmanagement.business.core.domain.model.Ship;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.FilmEntity;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntity;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntityModelFactory;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.repository.FilmsRepository;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.repository.ShipRepository;
import jakarta.persistence.PersistenceException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.empire.shipmanagement.infraestructure.adapter.out.persitence.adapter.ShipExampleFactory.buildPageable;
import static com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntityModelFactory.entityToModel;
import static com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntityModelFactory.modelToEntity;
@Component
@Log4j2
public class ShipPersistenceAdapter implements ShipBusinessPersistencePort {
    protected static final String ERROR_PERSISTING_THE_SHIP = "Error persisting the Ship with id: %s";
    protected static final String ERROR_UPDATING_THE_SHIP_NOT_FOUND = "Error updating the Ship with id: %s Ship not found";
    protected static final String ACCESS_LOG = "Access to the Persistence class for the method: %s";
    protected static final String ERROR_UPDATING_THE_FILM_NOT_FOUND = "Error updating the Ship due to film with id: %s not found";

    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private FilmsRepository filmRepository;

    @Override
    public Ship createShip(Ship newShip) {
        log.debug(String.format(ACCESS_LOG, "createShip"));
        return entityToModel(persistShipEntity(modelToEntity(newShip)));
    }
    private ShipEntity persistShipEntity(ShipEntity shipEntity) {
        try {
            return shipRepository.save(shipEntity);
        } catch (Exception e) {
            throw new PersistenceException(String.format(ERROR_PERSISTING_THE_SHIP, shipEntity.getId()));
        }
    }

    @Override
    public Ship updateShip(Ship ship) {
        log.debug(String.format(ACCESS_LOG, "updateShip"));
        Set<FilmEntity> filmEntities= null;
        shipRepository.findById(ship.getId())
                .orElseThrow(() -> new PersistenceException(
                        String.format(ERROR_UPDATING_THE_SHIP_NOT_FOUND, ship.getId())));
        if(Objects.nonNull(ship.getFilms()) && !ship.getFilms().isEmpty()){
            filmEntities = ship.getFilms().stream()
                    .map(filmId -> filmRepository.findById(filmId)
                                    .orElseThrow(() -> new PersistenceException(String.format(
                                            ERROR_UPDATING_THE_FILM_NOT_FOUND, filmId))))
                    .collect(Collectors.toSet());
        }
        ShipEntity shipEntity = modelToEntity(ship);
        shipEntity.setFilms(filmEntities);
        return  entityToModel(persistShipEntity(shipEntity));
    }

    @Override
    public void deleteShipById(Long shipId) {
        log.debug(String.format(ACCESS_LOG,"deleteShipById"));
        shipRepository.deleteById(shipId);
    }
    @Override
    public Optional<Ship> getShipById(Long shipId) {
        log.debug(String.format(ACCESS_LOG, "getShipById"));
        return Optional.ofNullable(shipRepository.findById(shipId)
                .map(ShipEntityModelFactory::entityToModel)
                .orElseThrow(() -> new PersistenceException(String.format(ERROR_UPDATING_THE_SHIP_NOT_FOUND, shipId))));
    }

    @Override
    public boolean shipExist(Long shipId) {
        log.debug(String.format(ACCESS_LOG, "shipExist"));
        return shipRepository.existsById(shipId);
    }

    @Override
    public Page<Ship> findShips(ShipFilter shipFilter) {
        log.debug(String.format(ACCESS_LOG, "findShips"));
        return Optional.of(shipFilter)
                .map(ShipExampleFactory::buildExample)
                .map(shipEntityExample -> shipRepository.findAll(shipEntityExample, buildPageable(shipFilter)))
                .map(page -> page.map(ShipEntityModelFactory::entityToModel))
                .orElseGet(Page::empty);
    }
}
