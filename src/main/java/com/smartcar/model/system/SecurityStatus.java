package com.smartcar.model.system;

import java.io.Serializable;
import java.util.List;

public class SecurityStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4933820482122106540L;
	
	private List<DoorSecurityInfo> list;

	
	public List<DoorSecurityInfo> getList() {
		return list;
	}

	
	public void setList(List<DoorSecurityInfo> list) {
		this.list = list;
	}
	
	

}
