package com.empire.shipmanagement.business.core.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ship {

    @Schema(name = "id",
            example = "12314",
            hidden = true)
    private Long id;

    @Schema(name = "name",
            example = "x-wing")
    @Pattern(regexp = "^[A-Za-z']+$", message = "Invalid name format")
    @NotNull(message = "The \"name\" field is required. Can not be null.")
    @NotBlank(message = "The \"name\" field is required. Can not be blank value.")
    @NotEmpty(message = "The \"name\" field is required. Can not be empty.")
    private String name;

    @Schema(name = "type",
            example = "destructor")
    @Pattern(regexp = "^[A-Za-z']+$", message = "Invalid type format")
    @NotNull(message = "The \"type\" field is required. Can not be null.")
    @NotBlank(message = "The \"type\" field is required. Can not be blank value.")
    @NotEmpty(message = "The \"type\" field is required. Can not be empty.")
    private ShipType type;

    @Schema(name = "status",
            example = "ACTIVE")
    @Pattern(regexp = "^[A-Za-z']+$", message = "Invalid type format")
    @NotNull(message = "The \"status\" field is required. Can not be null.")
    @NotBlank(message = "The \"status\" field is required. Can not be blank value.")
    @NotEmpty(message = "The \"status\" field is required. Can not be empty.")
    private ShipStatus status;

    @Schema(name = "description",
            example = "The destructor was destroyed at ... ")
    @Pattern(regexp = "^[A-Za-z0-9'.\\-_!#^~]+$", message = "Invalid description format")
    @NotNull(message = "The \"description\" field is required. Can not be null.")
    @NotBlank(message = "The \"description\" field is required. Can not be blank value.")
    @NotEmpty(message = "The \"description\" field is required. Can not be empty.")
    private String description;

    @Schema(name = "films",
            example = "[1,2,3]")
    private Set<Long> films;



    public Ship initNewShip() {
        this.status = ShipStatus.ACTIVE;
        this.type = ShipType.UNNASIGNED;
        return this;
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", films='" +  + '\'' +
                '}';
    }
}
