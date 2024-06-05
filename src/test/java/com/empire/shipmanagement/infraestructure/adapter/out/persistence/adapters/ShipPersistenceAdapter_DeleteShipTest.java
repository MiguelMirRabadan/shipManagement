package com.empire.shipmanagement.infraestructure.adapter.out.persistence.adapters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShipPersistenceAdapter_DeleteShipTest extends ShipPersistenceAdapterContext {

    @Test
    void deleteShipOk() {
        shipPersistenceAdapter.deleteShipById(ID);

        verify(shipRepository, times(1)).deleteById(any());
    }

    @Test
    void deleteShipThrowException() {
        doThrow(new RuntimeException()).when(shipRepository).deleteById(any());

        assertThrows(RuntimeException.class, () -> shipPersistenceAdapter.deleteShipById(ID));
    }


}