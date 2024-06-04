package com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity;


import com.empire.shipmanagement.business.core.application.dto.ShipFilter;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.mapper.ShipFilterMapper;

public class ShipEntityFilterFactory {
    public static ShipEntity filterToEntity(ShipFilter shipFilter) {
        return mapDefaultFields(shipFilter);
    }

    private static ShipEntity mapDefaultFields(ShipFilter shipFilter) {
        return new ShipFilterMapper().toLocalModel(shipFilter);
    }
}
