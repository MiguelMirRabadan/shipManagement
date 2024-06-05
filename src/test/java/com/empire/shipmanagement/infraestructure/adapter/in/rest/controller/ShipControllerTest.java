package com.empire.shipmanagement.infraestructure.adapter.in.rest.controller;

import com.empire.shipmanagement.business.core.application.dto.SearchResultDto;
import com.empire.shipmanagement.business.core.application.dto.ShipFilter;
import com.empire.shipmanagement.business.core.domain.model.ShipStatus;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.ShipInputFactory;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ShipControllerTest extends ShipControllerContext{

    @Test
    void createNewShip() {
        when(shipBusinessManagementPort.createShip(any())).thenReturn(ShipInputFactory.inputToModel(fullShipInput));

        ResponseEntity<Void> response = shipController.newShip(shipInput);
        
        assertEquals(201, response.getStatusCode().value());
        assertEquals(shipInput.getName(), ShipInputFactory.inputToModel(fullShipInput).getName());
        assertEquals(shipInput.getDescription(), ShipInputFactory.inputToModel(fullShipInput).getDescription());
        assertEquals(shipInput.getType(), ShipInputFactory.inputToModel(fullShipInput).getType());
        verify(shipBusinessManagementPort, times(1)).createShip(any());
        
    }

    @Test
    void updateShipOK() {
        this.fullShipInput.setStatus(ShipStatus.INACTIVE);
        when(shipBusinessManagementPort.updateShip(any())).thenReturn(ShipInputFactory.inputToModel(fullShipInput));
        assertDoesNotThrow(() -> shipController.updateShip(fullShipInput, ID));
        assertEquals(ShipStatus.INACTIVE, fullShipInput.getStatus());
        verify(shipBusinessManagementPort, times(1)).updateShip(any());
    }

    @Test
    void updateShipThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> shipController.updateShip(fullShipInput, NOT_EXIST_SHIP_ID));
    }

    @Test
    void getShipById() {
        when(shipBusinessManagementPort.retrieveShipByShipId(any())).thenReturn(ShipInputFactory.inputToModel(fullShipInput));
        shipController.getShipById(ID);
        verify(shipBusinessManagementPort, times(1)).retrieveShipByShipId(any());
    }

    @Test
    void deleteShip() {
        doNothing().when(shipBusinessManagementPort).deleteShipByShipId(any());
        shipController.deleteShip(ID);
        verify(shipBusinessManagementPort, times(1)).deleteShipByShipId(any());
    }

    @Test
    void findShips() {
        Page resultPage = buildResultPage();
        when(shipBusinessManagementPort.getShipSearchResult(any(ShipFilter.class)))
                .thenReturn(SearchResultDto.toDTO(resultPage));

        shipController.findShips(Optional.of(ID),Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(),
                0, 5, Optional.empty(), Optional.empty());
        verify(shipBusinessManagementPort, times(1)).getShipSearchResult(any(ShipFilter.class));
    }

    @Test
    void findUsersThrowSameExceptionAsPort() {
        String exceptionMessage = "exceptionMessage";
        when(shipBusinessManagementPort.getShipSearchResult(any())).thenThrow(new RuntimeException(exceptionMessage));

        Exception thrownException = assertThrows(RuntimeException.class, () ->
                shipController.findShips(Optional.of(ID),Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(),
                        0, 5, Optional.empty(), Optional.empty()));

        assertEquals(exceptionMessage, thrownException.getMessage());
    }
}