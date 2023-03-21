package com.priceapp.exception;

/**
 * 
 * @author mjpol
 *
 */
public class ResourceBadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode;

	/**
	 * ResourceBadRequestException
	 * 
	 * @param message
	 */
	public ResourceBadRequestException(String message) {
		super(message);
	}
	
	/**
	 * ResourceBadRequestException
	 * 
	 * @param message
	 * @param errorCode
	 */
	public ResourceBadRequestException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

