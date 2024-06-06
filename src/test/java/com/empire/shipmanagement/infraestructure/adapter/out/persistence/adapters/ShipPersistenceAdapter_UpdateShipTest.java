package com.empire.shipmanagement.infraestructure.adapter.out.persistence.adapters;


import com.empire.shipmanagement.business.core.domain.model.ShipStatus;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.ShipInputFactory;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShipPersistenceAdapter_UpdateShipTest extends ShipPersistenceAdapterContext{
    
    @Test
    void updateShipOK() {
        when(shipRepository.findById(any())).thenReturn(Optional.of(shipEntity));
        when(filmsRepository.findById(any())).thenReturn(Optional.of(filmEntity));
        when(shipRepository.save(any())).thenReturn(shipEntity);

        shipPersistenceAdapter.updateShip(ShipInputFactory.inputToModel(fullShipInput));

        assertEquals(ShipStatus.ACTIVE, fullShipInput.getStatus());
        verify(shipRepository, times(1)).save(any());
    }
    
    @Test
    void updateShipThrowException() {
        when(shipRepository.findById(any())).thenReturn(Optional.empty());

        Exception exception =  assertThrows(PersistenceException.class, () ->
                shipPersistenceAdapter.updateShip(ShipInputFactory.inputToModel(fullShipInput)));

        assertEquals(exception.getMessage(), String.format(ERROR_UPDATING_THE_SHIP_NOT_FOUND, fullShipInput.getId()));
        verify(shipRepository, times(1)).findById(any());

    }

    @Test
    void updateShipFilmsThrowException() {
        when(shipRepository.findById(any())).thenReturn(Optional.of(shipEntity));
        when(filmsRepository.findById(any())).thenReturn(Optional.empty());

        Exception exception =  assertThrows(PersistenceException.class, () ->
                shipPersistenceAdapter.updateShip(ShipInputFactory.inputToModel(fullShipInput)));

        assertEquals(exception.getMessage(), String.format(ERROR_UPDATING_THE_FILM_NOT_FOUND, fullShipInput.getId()));
        verify(shipRepository, times(1)).findById(any());

    }

}