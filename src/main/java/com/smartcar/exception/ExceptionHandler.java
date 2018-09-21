package com.smartcar.exception;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartcar.util.JsonUtil;

public class ExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
	
	// TODO: handle exception in a better way
	/**
	 * 
	 * @param e
	 * @return
	 */
	public static String generteResponse(Exception e) {
		LOGGER.error(e.getMessage());
		return JsonUtil.getJsonString("Internal server error");
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	public static String generteResponse(ExternalApiException e) {
		LOGGER.error(e.getMessage());
		return JsonUtil.getJsonString("Internal server error while accessing external apis");
	}
}
