package com.empire.shipmanagement.infraestructure.adapter.out.persitence.mapper;

import com.empire.shipmanagement.business.core.domain.model.Ship;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.FilmEntity;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ShipMapper extends DomainMapper<ShipEntity, Ship> {

    @Override
    public Ship toExternalModel(ShipEntity source) {
        Set<Long> filmIds = null;
        if (source.getFilms() == null || source.getFilms().isEmpty()) {
            // Si la lista de películas es nula o vacía, llamamos al método toExternalModel del superclase
            return super.toExternalModel(source);
        } else {
            // Si la lista de películas no es nula ni vacía, extraemos los IDs de las películas y creamos un Set<Long>
            filmIds = source.getFilms().stream()
                    .map(FilmEntity::getId)
                    .collect(Collectors.toSet());
        }
        return  Ship.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .type(source.getType())
                .status(source.getStatus())
                .films(filmIds)
                .build();
    }

}
