package com.empire.shipmanagement.infraestructure.adapter.out.persistence.adapters;

import com.empire.shipmanagement.business.core.domain.model.Ship;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShipPersistenceAdapter_GetShipByIdTest extends ShipPersistenceAdapterContext {
    @Test
    void getShipByIdOk() {
        when(shipRepository.findById(any())).thenReturn(Optional.of(shipEntity));
        Optional<Ship> result = shipPersistenceAdapter.getShipById(shipEntity.getId());
        assertNotNull(result);
        verify(shipRepository, times(1)).findById(any());
    }

}