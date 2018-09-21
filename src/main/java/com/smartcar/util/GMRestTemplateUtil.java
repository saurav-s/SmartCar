package com.smartcar.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.smartcar.exception.ExceptionHandler;
import com.smartcar.exception.ExternalApiException;
import com.smartcar.model.external.gm.ResponseModel;
import com.smartcar.model.external.gm.SecurityStatusModel;
import com.smartcar.model.external.gm.VehicleInfoModel;


public class GMRestTemplateUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
	
	private static final String URI = "http://gmapi.azurewebsites.net/";
	private static final String VEHICLE_INFO = "getVehicleInfoService";
	private static final String SECURITY_STATUS = "getSecurityStatusService";
	private static final String FUEL_BATTERY = "getEnergyService";
	private static final String ACTION_ENGINE = "actionEngineService";
	private static final String ID = "id";
	private static final String CONTENT_TYPE_VALUE = "application/json";
	private static final String RESPONSE_TYPE_KEY = "responseType";
	private static final String RESPONSE_TYPE_VALUE = "JSON";
	private static final String COMMAND_KEY="command";
	


	/**
	 * 
	 * @param id
	 * @return
	 */
	public static VehicleInfoModel getVehicleInfoFromGmApi(int id)throws ExternalApiException {
		Map<String, String> params = generateParams(id);
		HttpEntity<String> entity = generateHeaderEntity(params, MediaType.APPLICATION_JSON);
		ResponseModel<VehicleInfoModel> response = fetchResponseForVehicleInfo(entity, HttpMethod.POST, URI+VEHICLE_INFO);
		validateResponse(response);
		return response.getData();
	}
	
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static SecurityStatusModel getSecurityStatusFromGmApi(int id)throws ExternalApiException {
		Map<String, String> params = generateParams(id);
		HttpEntity<String> entity = generateHeaderEntity(params,MediaType.APPLICATION_JSON);
		ResponseModel<SecurityStatusModel> response = fetchResponseForSecurityStatus(entity, HttpMethod.POST, URI+SECURITY_STATUS);
		validateResponse(response);
		return response.getData();
	}


	/**
	 * 
	 * @param id
	 * @return
	 */
	private static Map<String, String> generateParams(int id) {
		return generateParams(id,RESPONSE_TYPE_VALUE);
	}
	
	/**
	 * 
	 * @param id
	 * @param responseType
	 * @return
	 */
	private static Map<String, String> generateParams(int id,String responseType) {
		Map<String, String> params = new HashMap<>();
		params.put(ID, String.valueOf(id));
		params.put(RESPONSE_TYPE_KEY, responseType);
		return params;
	}
	
	/**
	 * 
	 * @param id
	 * @param responseType
	 * @param command
	 * @return
	 */
	private static Map<String, String> generateParamsEngineAction(int id, String command) {
		Map<String, String> params = generateParams(id);
		params.put(COMMAND_KEY, command);
		return params;
	}
	



	/**
	 * Generates header entity including payload
	 * 
	 * @param id
	 * @return headerEntity
	 */
	public static HttpEntity<String> generateHeaderEntity(Map<String, String> params, MediaType mediaType) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		return new HttpEntity<String>(generatePayload(params), headers);
	}



	/**
	 * Generates payload json string
	 * 
	 * @param id
	 * @return
	 */
	private static String generatePayload(Map<String, String> params) {
		return JsonUtil.getJsonString(params);
	}
	
	
	
	/**
	 * 
	 * @param entity
	 * @param method
	 * @param uri
	 * @return
	 */
	private static ResponseModel<VehicleInfoModel> fetchResponseForVehicleInfo(HttpEntity<String> entity, HttpMethod method, String uri) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResponseModel<VehicleInfoModel>> response = restTemplate.exchange(uri, method, entity,
				new ParameterizedTypeReference<ResponseModel<VehicleInfoModel>>() {
				});
		return response.getBody();
	}
	
	/**
	 * 
	 * @param entity
	 * @param method
	 * @param uri
	 * @return
	 */
	private static ResponseModel<SecurityStatusModel> fetchResponseForSecurityStatus(HttpEntity<String> entity, HttpMethod method, String uri) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResponseModel<SecurityStatusModel>> response = restTemplate.exchange(uri, method, entity,
				new ParameterizedTypeReference<ResponseModel<SecurityStatusModel>>() {
				});
		return response.getBody();
	}
	
	
	/**
	 * Validates response status.
	 * @param response
	 * @throws HttpServerErrorException
	 */
	private static void validateResponse(ResponseModel response) throws ExternalApiException{
		if(!response.getStatus().equals("200")) {
			LOGGER.error("External server responded with error, error code is :"+response.getStatus()+ " service: "+response.getService());
			throw new ExternalApiException("External server responded with error");
		}
	}
	



}
