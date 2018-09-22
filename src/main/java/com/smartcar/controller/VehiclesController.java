package com.smartcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcar.model.system.EngineAction;
import com.smartcar.service.VehicleService;

/**
 * 
 * @author sanketsaurav
 *
 */


@RestController
@RequestMapping("/vehicles")
public class VehiclesController {
	
	@Autowired
	private VehicleService vehicleService;
	
	/**
	 * 
	 * @param vehicleId
	 * @return VehicleInfo json string
	 */
	@GetMapping("{id}")
	public String getVehicleInfo(@PathVariable("id") int vehicleId) {
		return vehicleService.getVehicleInfo(vehicleId);
	}
	
	
	
	/**
	 * 
	 * @param vehicleId
	 * @return SecurityInfo json string
	 */
	@GetMapping("{id}/doors")
	public String getVehicleSecurityInfo(@PathVariable("id") int vehicleId) {
		return vehicleService.getVehicleSecurityInfo(vehicleId);
	}
	
	
	/**
	 * 
	 * @param vehicleId
	 * @return Fuel info json string
	 */
	@GetMapping("{id}/fuel")
	public String getFuelInfo(@PathVariable("id") int vehicleId) {
		return vehicleService.getVehicleFuelInfo(vehicleId);
	}
	
	
	/**
	 * 
	 * @param vehicleId
	 * @return Battery info json string
	 */
	@GetMapping("{id}/battery")
	public String getBatteryInfo(@PathVariable("id") int vehicleId) {
		return vehicleService.getVehicleBatteryInfo(vehicleId);
	}
	
	/**
	 * 
	 * @param vehicleId
	 * @return Engine Action response json string
	 */
	@PostMapping(path="{id}/engine", consumes = "application/json")
	public String perfromEngineAction(@PathVariable("id") int vehicleId, @RequestBody EngineAction action) {
		return vehicleService.performEngineEaction(vehicleId, action.getAction());
	}

}
