package com.empire.shipmanagement.infraestructure.adapter.out.persistence.adapters;

import com.empire.shipmanagement.business.core.domain.model.ShipStatus;
import com.empire.shipmanagement.business.core.domain.model.ShipType;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.FullShipInput;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.ShipInput;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.ShipInputFactory;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.adapter.ShipPersistenceAdapter;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.FilmEntity;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntity;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntityFilterFactory;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity.ShipEntityModelFactory;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.repository.FilmsRepository;
import com.empire.shipmanagement.infraestructure.adapter.out.persitence.repository.ShipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@ExtendWith(SpringExtension.class)
class ShipPersistenceAdapterContext {
    @InjectMocks
    protected ShipPersistenceAdapter shipPersistenceAdapter;
    @Mock
    protected ShipRepository shipRepository;
    @Mock
    protected FilmsRepository filmsRepository;
    protected ShipInput shipInput;
    protected FullShipInput fullShipInput;
    protected ShipEntity shipEntity;
    protected FilmEntity filmEntity;



    protected final String NAME = "Z-wing";
    protected final String DESCRIPTION = "blablabla";
    protected final ShipStatus STATUS = ShipStatus.ACTIVE;
    protected final ShipType TYPE = ShipType.DESTROYER;
    protected final Long ID = 1L;
    protected final Set<Long> FILMS_ID = Set.of(1L);

    protected static final String ERROR_PERSISTING_THE_SHIP = "Error persisting the Ship with id: %s";
    protected static final String ERROR_FINDING_SHIPS = "Error finding the Ships";
    protected static final String ERROR_UPDATING_THE_SHIP_NOT_FOUND = "Error updating the Ship with id: %s Ship not found";
    protected static final String ACCESS_LOG = "Access to the Persistence class for the method: %s";
    protected static final String ERROR_UPDATING_THE_FILM_NOT_FOUND = "Error updating the Ship due to film with id: %s not found";
    @BeforeEach
    void setUp() {
        shipInput = this.buildShipInput();
        fullShipInput = this.buildFullShipInput();
        filmEntity =  this.buildFilmEntity();
        shipEntity = this.buildShipEntity();
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

    public  ShipEntity buildShipEntity() {
        return  ShipEntity.builder()
                .id(ID)
                .description(DESCRIPTION)
                .type(TYPE)
                .status(STATUS)
                .name(NAME)
                .films(Set.of(filmEntity))
                .build();
    }
    public  FilmEntity buildFilmEntity() {
        return  FilmEntity.builder()
                .id(ID)
                .description(DESCRIPTION)
                .name("FilmName")
                .build();
    }


}