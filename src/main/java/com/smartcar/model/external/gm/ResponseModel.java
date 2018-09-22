package com.smartcar.model.external.gm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseModel<T> {

	private String service;
	private String status;
	private T data;
	private EngineActionResponseModel actionResult;


	public EngineActionResponseModel getActionResult() {
		return actionResult;
	}

	public void setActionResult(EngineActionResponseModel actionResult) {
		this.actionResult = actionResult;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
