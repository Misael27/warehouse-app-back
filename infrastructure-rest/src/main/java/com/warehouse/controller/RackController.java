package com.warehouse.controller;

import com.warehouse.entity.Rack;
import com.warehouse.exception.ErrorDetails;
import com.warehouse.mapper.Mapper;
import com.warehouse.request.RackRequest;
import com.warehouse.usecase.rack.CreateRackUseCase;
import com.warehouse.usecase.rack.DeleteRackUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rack")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RackController {

  private final CreateRackUseCase createRackUseCase;
  private final DeleteRackUseCase deleteRackUseCase;

  private final Mapper<Rack, RackRequest, RackRequest, Rack> mapper;

  public RackController(CreateRackUseCase createRackUseCase, DeleteRackUseCase deleteRackUseCase) {
    this.createRackUseCase = createRackUseCase;
    this.deleteRackUseCase = deleteRackUseCase;
    this.mapper = new Mapper<>(Rack.class, RackRequest.class, RackRequest.class, Rack.class);
  }
  
  @Operation(summary = "Create rack", tags = { "Rack" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = Rack.class))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
  @PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<Rack> postCreateRack(final @Parameter(description = "Rack create", required = true) @RequestBody @Valid RackRequest rackRequest) {
    final Rack rack = mapper.mapDtoToEntity(rackRequest);
    this.createRackUseCase.execute(rack);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapEntityToResponse(rack));
  }

  @Operation(summary = "Delete rack by id", tags = { "Rack" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true))),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
      @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
  @DeleteMapping(value = "/{rackId}", produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<Void> deleteById(
      final @Parameter(description = "Rack ID", in = ParameterIn.PATH, required = true) @PathVariable("rackId") Integer rackId) {
    this.deleteRackUseCase.execute(rackId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
