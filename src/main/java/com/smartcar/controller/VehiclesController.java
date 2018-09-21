package com.smartcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 * @return VehicleInfo json string
	 */
	@GetMapping("{id}/doors")
	public String getVehicleSecurityInfo(@PathVariable("id") int vehicleId) {
		return vehicleService.getVehicleSecurityInfo(vehicleId);
	}

}
