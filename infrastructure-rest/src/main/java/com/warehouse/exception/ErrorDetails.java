package com.warehouse.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

/**
 * ErrorDetails
 * 
 * @author mjpol
 *
 */
public class ErrorDetails {
	
	private Date timestamp;
	private String message;
	private int status;
	private HttpStatus error;
	
	public ErrorDetails(Date timestamp, String message, int status, HttpStatus error) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.error = error;
	}

	public HttpStatus getError() {
		return error;
	}

	public void setError(HttpStatus error) {
		this.error = error;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
