package com.smartcar.model.external.gm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleInfoModel {
	private TypeValuePair vin;
	private TypeValuePair color;
	private TypeValuePair fourDoorSedan;
	private TypeValuePair twoDoorCoupe;
	private TypeValuePair driveTrain;
	
	public TypeValuePair getVin() {
		return vin;
	}
	
	public void setVin(TypeValuePair vin) {
		this.vin = vin;
	}
	
	public TypeValuePair getColor() {
		return color;
	}
	
	public void setColor(TypeValuePair color) {
		this.color = color;
	}
	
	public TypeValuePair getFourDoorSedan() {
		return fourDoorSedan;
	}
	
	public void setFourDoorSedan(TypeValuePair fourDoorSedan) {
		this.fourDoorSedan = fourDoorSedan;
	}
	
	public TypeValuePair getTwoDoorCoupe() {
		return twoDoorCoupe;
	}
	
	public void setTwoDoorCoupe(TypeValuePair twoDoorCoupe) {
		this.twoDoorCoupe = twoDoorCoupe;
	}
	
	public TypeValuePair getDriveTrain() {
		return driveTrain;
	}
	
	public void setDriveTrain(TypeValuePair driveTrain) {
		this.driveTrain = driveTrain;
	}
	
}
