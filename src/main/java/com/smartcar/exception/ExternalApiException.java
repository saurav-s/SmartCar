package com.smartcar.exception;

/**
 * 	
 * @author sanketsaurav
 *
 */
public class ExternalApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 * @param errorMessage
	 */
	public ExternalApiException(String errorMessage) {
		super(errorMessage);
	}


	/**
	 * 
	 * @param errorMessage
	 * @param err
	 */
	public ExternalApiException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
