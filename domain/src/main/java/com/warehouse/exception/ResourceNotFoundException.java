package com.warehouse.exception;

/**
 * NotFoundException
 * 
 * @author mjpol
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	/**
	 * ResourceNotFoundException
	 * 
	 * @param message
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * ResourceNotFoundException
	 * 
	 * @param message
	 * @param errorCode
	 */
	public ResourceNotFoundException(String message, String errorCode) {
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
