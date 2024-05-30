package com.warehouse.controller;

import com.warehouse.entity.Rack;
import com.warehouse.exception.ErrorDetails;
import com.warehouse.mapper.Mapper;
import com.warehouse.request.RackRequest;
import com.warehouse.usecase.rack.CreateRackUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rack")
public class RackController {

  private final CreateRackUseCase createRackUseCase;

  private final Mapper<Rack, RackRequest, RackRequest, Rack> mapper;

  public RackController(CreateRackUseCase createRackUseCase) {
    this.createRackUseCase = createRackUseCase;
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

}
