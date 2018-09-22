package com.smartcar.service;

import com.smartcar.model.system.enums.Action;

public interface VehicleService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getVehicleInfo(int id);

	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getVehicleFuelInfo(int id);

	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getVehicleSecurityInfo(int id);

	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getVehicleBatteryInfo(int id);

	
	/**
	 * 
	 * @param id
	 * @param action
	 * @return
	 */
	public String performEngineEaction(int id, Action action);

}
