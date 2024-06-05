package com.empire.shipmanagement.infraestructure.adapter.out.persistence.adapters;


import com.empire.shipmanagement.business.core.domain.model.Ship;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.ShipInputFactory;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShipPersistenceAdapter_CreateShipTest extends ShipPersistenceAdapterContext{
    
    @Test
    void createNewShipOk() {
        when(shipRepository.save(any())).thenReturn(shipEntity);

        Ship ship = shipPersistenceAdapter.createShip(ShipInputFactory.inputToModel(shipInput));

        assertNotNull(ship.getId());
        verify(shipRepository, times(1)).save(any());
    }
    
    @Test
    void createNewShipThrowsPersistenceException() {
        when(shipRepository.save(any())).thenThrow(new RuntimeException("Error saving the Ship"));

        PersistenceException exception =  assertThrows(PersistenceException.class, () ->
                shipPersistenceAdapter.createShip(ShipInputFactory.inputToModel(shipInput)));

        assertEquals(exception.getMessage(), String.format(ERROR_PERSISTING_THE_SHIP,   shipInput.getId()));
        verify(shipRepository, times(1)).save(any());
    }
    

}