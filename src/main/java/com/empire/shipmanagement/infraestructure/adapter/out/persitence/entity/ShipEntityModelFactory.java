package com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity;


import com.empire.shipmanagement.business.core.domain.model.Ship;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.mapper.ShipMapper;

public class ShipEntityModelFactory {

    public static ShipEntity modelToEntity(Ship ship) {
        return new ShipMapper().toLocalModel(ship);
    }

    public static Ship entityToModel(ShipEntity entity) {
        return new ShipMapper().toExternalModel(entity);

    }
}
