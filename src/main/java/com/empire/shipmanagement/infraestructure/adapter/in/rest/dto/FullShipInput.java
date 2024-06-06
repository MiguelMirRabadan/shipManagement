package com.empire.shipmanagement.infraestructure.adapter.in.rest.dto;

import com.empire.shipmanagement.business.core.domain.model.ShipStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FullShipInput extends ShipInput {


    @NotNull(message = "The status is required.")
    @Schema(name = "status",
            type = "ShipStatus",
            example = "ACTIVE")
    private ShipStatus status;

    @NotEmpty(message = "The films are required.")
    @Schema(name = "films",
            type = "Set<Long>",
            example = "[1, 2, 3]")
    private Set<Long> films;

}
