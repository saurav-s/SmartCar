package com.smartcar.model.system;

import java.io.Serializable;

public class VehicleInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4937138204822106540L;
	
	private String vin;
	private String color;
	private int doorCount;
	private String driveTrain;
	
	
	public String getVin() {
		return vin;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getDoorCount() {
		return doorCount;
	}
	
	public void setDoorCount(int doorCount) {
		this.doorCount = doorCount;
	}
	
	public String getDriveTrain() {
		return driveTrain;
	}
	
	public void setDriveTrain(String driveTrain) {
		this.driveTrain = driveTrain;
	}

}
