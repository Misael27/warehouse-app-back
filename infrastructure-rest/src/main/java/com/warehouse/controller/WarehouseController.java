package com.warehouse.controller;

import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ErrorDetails;
import com.warehouse.mapper.Mapper;
import com.warehouse.request.WarehouseRequest;
import com.warehouse.usecase.warehose.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class WarehouseController {

  private final CreateWarehouseUseCase createWarehouseUseCase;
  private final GetWarehouseByIdUseCase getWarehouseByIdUseCase;
  private final ListWarehouseUseCase listWarehouseUseCase;
  private final UpdateWarehouseUseCase updateWarehouseUseCase;
  private final DeleteWarehouseUseCase deleteWarehouseUseCase;
  private final GetRackPermutationsUseCase getRackPermutationsUseCase;
  private final Mapper<Warehouse,WarehouseRequest,WarehouseRequest,Warehouse> mapper;

  public WarehouseController(CreateWarehouseUseCase createWarehouseUseCase, GetWarehouseByIdUseCase getWarehouseByIdUseCase,
                             ListWarehouseUseCase listWarehouseUseCase, UpdateWarehouseUseCase updateWarehouseUseCase,
                             DeleteWarehouseUseCase deleteWarehouseUseCase, GetRackPermutationsUseCase getRackPermutationsUseCase) {
    this.createWarehouseUseCase = createWarehouseUseCase;
    this.getWarehouseByIdUseCase = getWarehouseByIdUseCase;
    this.listWarehouseUseCase = listWarehouseUseCase;
    this.updateWarehouseUseCase = updateWarehouseUseCase;
    this.deleteWarehouseUseCase = deleteWarehouseUseCase;
    this.getRackPermutationsUseCase = getRackPermutationsUseCase;
    this.mapper = new Mapper<>(Warehouse.class, WarehouseRequest.class, WarehouseRequest.class, Warehouse.class);
  }
  
  @Operation(summary = "Create warehouse", tags = { "Warehouse" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = Warehouse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
  @PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<Warehouse> postCreate(final @Parameter(description = "Warehouse create", required = true) @RequestBody @Valid WarehouseRequest warehouseRequest) {
    final Warehouse warehouse = mapper.mapDtoToEntity(warehouseRequest);
    this.createWarehouseUseCase.execute(warehouse);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapEntityToResponse(warehouse));
  }

  @Operation(summary = "Get warehouse by id", tags = { "Warehouse" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Warehouse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
  @GetMapping(value = "/{warehouseId}", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<Warehouse> getWarehouseById(
      final @Parameter(description = "WAREHOUSE ID", in = ParameterIn.PATH, required = true) @PathVariable("warehouseId") Integer warehouseId) {
    return ResponseEntity.status(HttpStatus.OK).body(mapper.mapEntityToResponse(this.getWarehouseByIdUseCase.execute(warehouseId)));
  }

  @Operation(summary = "Warehouse list", tags = { "Warehouse" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK", content =
      @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE,
          array = @ArraySchema(schema = @Schema(implementation = Warehouse.class)))),
  })
  @GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<List<Warehouse>> list() {
    return ResponseEntity.status(HttpStatus.OK).body(mapper.mapAllEntityToResponse(this.listWarehouseUseCase.execute()));
  }

  @Operation(summary = "Update warehouse", tags = { "Warehouse" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Warehouse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
  @PutMapping(value = "/{warehouseId}", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<Warehouse> putUpdate(
      final @Parameter(description = "WAREHOUSE ID", in = ParameterIn.PATH, required = true) @PathVariable("warehouseId") Integer warehouseId,
      final @Parameter(description = "warehouse update", required = true) @RequestBody @Valid WarehouseRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(mapper.mapEntityToResponse(this.updateWarehouseUseCase.execute(warehouseId, mapper.mapDtoUpdateToEntity(request))));
  }

  @Operation(summary = "Delete warehouse by id", tags = { "Warehouse" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
  @DeleteMapping(value = "/{warehouseId}", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<Void> deleteById(
      final @Parameter(description = "Warehouse ID", in = ParameterIn.PATH, required = true) @PathVariable("warehouseId") Integer warehouseId) {
    this.deleteWarehouseUseCase.execute(warehouseId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Get permutations rack type by warehouse", tags = { "Warehouse" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = List.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
  @GetMapping(value = "/permutations/{warehouseId}", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<List<String>> getRackPermutations(
      final @Parameter(description = "WAREHOUSE ID", in = ParameterIn.PATH, required = true) @PathVariable("warehouseId") Integer warehouseId) {
    return ResponseEntity.status(HttpStatus.OK).body(this.getRackPermutationsUseCase.execute(warehouseId));
  }
  
}
