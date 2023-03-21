package com.priceapp.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.priceapp.exception.ResourceBadRequestException;
import com.priceapp.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;

/**
 * GlobalExceptionHandler
 * 
 * @author mjpol
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * resourceNotFoundHandling
	 * 
	 * @param exception
	 * @param request
	 * @return ResponseEntity<?>
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request) {
		final ErrorDetails errorDetails = new ErrorDetails(
				new Date(), 
				exception.getMessage(),
				404,
				HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * resourceNotFoundHandling
	 * 
	 * @param exception
	 * @param request
	 * @return ResponseEntity<?>
	 */
	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<?> resourceBadRequestHandling(ResourceBadRequestException exception, WebRequest request) {
		final ErrorDetails errorDetails = new ErrorDetails(
				new Date(), 
				exception.getMessage(),
				400,
				HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * methodArgumentNotValidExceptionHandling
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidExceptionHandling(MethodArgumentNotValidException exception, WebRequest request) {
		final ErrorDetails errorDetails = new ErrorDetails(
				new Date(), 
				"INVALID_REQUEST_PARAMETERS", 
				400,
				 HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * globalExceptionHandling
	 * 
	 * @param exception
	 * @param request 
	 * @return ResponseEntity<?>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
		logger.error(exception.getMessage(), exception);
		final ErrorDetails errorDetails = new ErrorDetails(
				new Date(), 
				exception.getMessage(), 
				500,
				 HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
