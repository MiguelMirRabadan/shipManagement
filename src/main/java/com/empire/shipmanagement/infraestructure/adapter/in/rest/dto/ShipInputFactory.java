package com.empire.shipmanagement.infraestructure.adapter.in.rest.dto;

import com.empire.shipmanagement.business.core.domain.model.Ship;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.mapper.DomainMapper;

public class ShipInputFactory {

    public static Ship inputToModel(ShipInput shipInput) {
        return new DomainMapper<ShipInput, Ship>() {
        }.toExternalModel(shipInput);
    }

}
