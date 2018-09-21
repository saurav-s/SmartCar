package com.smartcar.model.system;

import java.io.Serializable;

public class DoorSecurityInfo implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4804039888953392603L;
	
	private String location;
	private boolean locked;
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	

}
