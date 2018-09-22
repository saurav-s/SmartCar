package com.smartcar.model.system.enums;

public enum ActionResponse {
	SUCCESS("success"), ERROR("error");

	private String value;

	public String getValue() {
		return this.value;
	}

	ActionResponse(String value) {
		this.value = value;
	}
}
