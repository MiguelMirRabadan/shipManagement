package com.empire.shipmanagement.infraestructure.adapter.in.rest.dto;

import com.empire.shipmanagement.business.core.domain.model.ShipType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ShipInput {

    @Schema(name = "id",
            type = "Long",
            example = "132")
    @Min(value = 1, message = "The id must be greater than or equal to 1.")
    @NotNull(message = "The id is required. Couldn't be null")
    private Long id;

    @NotEmpty(message = "The name is required.  Couldn't be empty")
    @NotBlank(message = "The name is required. Couldn't be blank")
    @NotNull(message = "The name is required. Couldn't be null")
    @Pattern(regexp = "^[A-Za-z0-9'.\\-_!#^~\\s]+$", message = "Invalid input format")
    @Size(min = 1, max = 100, message = "The length of the Ship name must be between 1 and 100 characters.")
    @Schema(name = "name",
            type = "String",
            example = "x-wing")
    private String name;

    @NotEmpty(message = "The description is required.  Couldn't be empty")
    @NotBlank(message = "The description is required. Couldn't be blank")
    @NotNull(message = "The description is required. Couldn't be null")
    @Pattern(regexp = "^[A-Za-z0-9'.\\-_!#^~\\s]+$", message = "Invalid input format")
    @Size(min = 1, max = 100, message = "The length of the Ship description must be between 1 and 100 characters.")
    @Schema(name = "description",
            type = "String",
            example = "The Ship blablablabla")
    private String description;

    @NotNull(message = "The type is required. Couldn't be null")
    @Schema(name = "type",
            type = "ShipType",
            example = "FIGHTER")
    private ShipType type;

}
