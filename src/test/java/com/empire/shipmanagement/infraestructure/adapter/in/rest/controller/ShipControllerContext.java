package com.empire.shipmanagement.infraestructure.adapter.in.rest.controller;

import com.empire.shipmanagement.business.core.application.port.in.ShipBusinessManagementPort;
import com.empire.shipmanagement.business.core.domain.model.Ship;
import com.empire.shipmanagement.business.core.domain.model.ShipStatus;
import com.empire.shipmanagement.business.core.domain.model.ShipType;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.FullShipInput;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.ShipInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@ExtendWith(SpringExtension.class)
class ShipControllerContext {
    
    @InjectMocks
    protected ShipController shipController;
    @Mock
    protected ShipBusinessManagementPort shipBusinessManagementPort;
    
    protected ShipInput shipInput;
    protected FullShipInput fullShipInput;

    protected final String NAME = "Z-wing";
    protected final String DESCRIPTION = "blablabla";
    protected final ShipStatus STATUS = ShipStatus.ACTIVE;
    protected final ShipType TYPE = ShipType.DESTROYER;
    protected final Long ID = 1L;
    protected final Set<Long> FILMS_ID = Set.of(1L);

    protected final Long NOT_EXIST_SHIP_ID = 2L;
    
    
    @BeforeEach
    void setUp() {
        shipInput = this.buildShipInput();
        fullShipInput = this.buildFullShipInput();
    }
    
    public ShipInput buildShipInput() {
        return ShipInput.builder()
                .id(ID)
                .description(DESCRIPTION)
                .type(TYPE)
                .name(NAME)
                .build();
    }

    public FullShipInput buildFullShipInput() {
        return  FullShipInput.builder()
                .id(ID)
                .description(DESCRIPTION)
                .type(TYPE)
                .status(STATUS)
                .name(NAME)
                .films(FILMS_ID)
                .build();
    }

    public Page<Ship> buildResultPage() {
        return Page.empty(Pageable.ofSize(10).withPage(0).first());
    }
}