package com.empire.shipmanagement.infraestructure.adapter.out.persistence.adapters;

import com.empire.shipmanagement.business.core.application.dto.ShipFilter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShipPersistenceAdapter_FindShipsTest extends ShipPersistenceAdapterContext {

    @Test
    void findShipsOk() {
        ShipFilter filter = ShipFilter.builder().pageIndex(0).pageSize(10).build();
        when(shipRepository.findAll(any(),any(Pageable.class))).thenReturn(Page.empty());
        shipPersistenceAdapter.findShips(filter);

        verify(shipRepository, times(1)).findAll(ArgumentMatchers.any(Example.class),
                ArgumentMatchers.any(Pageable.class));
    }

    @Test
    void findShipsThrowsSameExceptionAsRepository(){

        ShipFilter filter = ShipFilter.builder().pageIndex(0).pageSize(10).build();
        when(shipRepository.findAll(any(Example.class),any(Pageable.class))).thenThrow(new RuntimeException(ERROR_FINDING_SHIPS));
        assertThrows(RuntimeException.class, () -> shipPersistenceAdapter.findShips(filter));

        verify(shipRepository, times(1)).findAll(ArgumentMatchers.any(Example.class),
                ArgumentMatchers.any(Pageable.class));

    }

}