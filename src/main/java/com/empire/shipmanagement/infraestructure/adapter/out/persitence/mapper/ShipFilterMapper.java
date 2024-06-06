package com.empire.shipmanagement.infraestructure.adapter.out.persitence.mapper;

import com.empire.shipmanagement.business.core.application.dto.ShipFilter;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.FilmEntity;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ShipFilterMapper extends DomainMapper<ShipEntity, ShipFilter> {

    @Override
    public ShipFilter toExternalModel(ShipEntity source) {
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
        return  ShipFilter.builder()
                .id(Optional.of(source.getId()))
                .name(Optional.of(source.getName()))
                .description(Optional.of(source.getDescription()))
                .type(Optional.of(source.getType()))
                .status(Optional.of(source.getStatus()))
                .films(Optional.of(filmIds))
                .build();
    }

}
