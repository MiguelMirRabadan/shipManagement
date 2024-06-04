package com.empire.shipmanagement.infraestructure.adapter.out.persitence.entity;

import com.empire.shipmanagement.business.core.domain.model.Ship;
import com.empire.shipmanagement.business.core.domain.model.ShipStatus;
import com.empire.shipmanagement.business.core.domain.model.ShipType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ships")
public class ShipEntity {

    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private ShipType type;
    @Enumerated(EnumType.STRING)
    private ShipStatus status;
    @Column(nullable = false)
    private String description;
    @ManyToMany
    @JoinTable(
            name = "ship_film",
            joinColumns = @JoinColumn(name = "ship_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private Set<FilmEntity> films;

    /**
     * Column names used to create specific criterias (search by filter)
     */
    public static final String ID_COLUMN_PATH = "id";
    public static final String DESCRIPTION_COLUMN_PATH = "description";
    public static final String NAME_COLUMN_PATH = "name";
    public static final String TYPE_COLUMN_PATH = "type";
    public static final String STATUS_COLUMN_PATH = "status";
}
