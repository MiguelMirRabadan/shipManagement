package com.empire.shipmanagement.infraestructure.adapter.in.rest.api;

import com.empire.shipmanagement.business.core.application.dto.SearchResultDto;
import com.empire.shipmanagement.business.core.domain.model.Ship;
import com.empire.shipmanagement.business.core.domain.model.ShipStatus;
import com.empire.shipmanagement.business.core.domain.model.ShipType;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.aspect.LogIfNegative;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.FullShipInput;
import com.empire.shipmanagement.infraestructure.adapter.in.rest.dto.ShipInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RequestMapping("ship")
@Tag(name = "ship", description = "Ship API")
public interface ShipApi {

    @Operation(
            summary = "Create new Ship",
            description = "Tries to create a new Ship all field are required")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ship registered"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PostMapping
    ResponseEntity<Void> newShip(@Valid @RequestBody final ShipInput shipInput);

    @Operation(
            summary = "Update Ship",
            description = "tries to update the data of an Ship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ship is updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PutMapping(path = "/{id}")
    Ship updateShip(@Valid @RequestBody final FullShipInput fullShipInput, @LogIfNegative @PathVariable("id") final Long id);

    @Operation(
            summary = "get Ship",
            description = "tries to search a Ship by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ship is found"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping(path = "/{id}")
    Ship getShipById(@LogIfNegative @PathVariable("id") final Long id);

    @Operation(
            summary = "delete Ship",
            description = "tries to delete a Ship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ship is deleted"),
            @ApiResponse(responseCode = "500", description = "No value present"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping(path = "/{id}")
    void deleteShip(@LogIfNegative @PathVariable("id") final Long id);

    @Operation(
            summary = "find Ships",
            description = "Tries to find Ships filtered by several parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a list of Ships that match the filter it may be ampty or not"),
            @ApiResponse(responseCode = "500", description = "Unexpected error"),
    })
    @GetMapping
    SearchResultDto findShips(@LogIfNegative @RequestParam Optional<Long> id,
                              @RequestParam Optional<String> name,
                              @RequestParam Optional<ShipType> type,
                              @RequestParam Optional<ShipStatus> status,
                              @RequestParam Optional<String> description,
                              @LogIfNegative @RequestParam Optional<Set<Long>> films,
                              @LogIfNegative @RequestParam Integer pageIndex,
                              @LogIfNegative  @RequestParam Integer pageSize,
                              @RequestParam Optional<String> sortField,
                              @RequestParam Optional<Sort.Direction> sortDirection);
}

