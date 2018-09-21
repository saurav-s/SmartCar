package com.smartcar.model.external.gm;

import java.util.List;

public class DoorSecurityModels {
	
	private String type;
	private List<DoorSecurityModel> values;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	
	public List<DoorSecurityModel> getValues() {
		return values;
	}

	
	public void setValue(List<DoorSecurityModel> values) {
		this.values = values;
	}

	

}
