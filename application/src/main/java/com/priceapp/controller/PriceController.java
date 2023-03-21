package com.priceapp.controller;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.priceapp.exception.ErrorDetails;
import com.priceapp.response.FinalPriceResponse;
import com.priceapp.service.IPriceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * 
 * @author mjpol
 *
 */
@RestController
@RequestMapping("/price")
public class PriceController {
	
	private IPriceService priceService;
	ModelMapper mapper;
	
	public PriceController(IPriceService priceService) {
		this.priceService = priceService;
		this.mapper = new ModelMapper();
		this.mapper.getConfiguration().setSkipNullEnabled(true);
	}
	
	/**
	 * 
	 * @param productId
	 * @param brandId
	 * @param applicationDate
	 * @return
	 */
	@Operation(summary = "Get price by productId, BrandId and Date", tags = { "Price" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = FinalPriceResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))})
	@GetMapping(value = "/product/{productId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<FinalPriceResponse> getPriceByProductIdBrandIdDate(
			final @Parameter(description = "Price ID", in = ParameterIn.PATH, required = true) @PathVariable("productId") Integer productId,
			final @Parameter(description = "Brand ID", in = ParameterIn.QUERY, required = true) @RequestParam("brandId") Integer brandId,
			final @Parameter(description = "Application date", in = ParameterIn.QUERY, required = true, example = "2020-06-14 14:00:00") @RequestParam("applicationDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate) {
		return ResponseEntity.status(HttpStatus.OK).body(mapper.map(priceService.findFinalPrice(productId, brandId, applicationDate), FinalPriceResponse.class));
	}   
	
}

