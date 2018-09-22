package com.smartcar.controller.Service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.smartcar.model.system.VehicleInfo;
import com.smartcar.service.VehicleService;
import com.smartcar.service.VehicleServiceImpl;

@RunWith(SpringRunner.class)
public class VehicleServiceTests {
	
	@Autowired
	VehicleService service;

	   @TestConfiguration
	    static class VehicleServiceImplTestContextConfiguration {
	  
	        @Bean
	        public VehicleService vehicleService() {
	            return new VehicleServiceImpl();
	        }
	    }
	   
	@Test
	public void testVehicleInfo() throws Exception {
		int id = 1234;
		VehicleInfo info = new VehicleInfo();
		info.setColor("Metallic Silver");
		info.setDoorCount(4);
		info.setDriveTrain("v8");
		info.setVin("123123412412");
		String vehicleInfo = service.getVehicleInfo(id);
		Gson gson =new Gson();
		VehicleInfo response = gson.fromJson(vehicleInfo, VehicleInfo.class);
		assertEquals(info.getColor(), response.getColor());
		assertEquals(info.getDoorCount(), response.getDoorCount());
		assertEquals(info.getDriveTrain(), response.getDriveTrain());
		assertEquals(info.getVin(), response.getVin());

	}
	
	@Test
	public void testVehicleInfoError() throws Exception {
		int invalidId = 7823444;
		
		String response = service.getVehicleInfo(invalidId);
		Gson gson =new Gson();
		String fromJson = gson.fromJson(response, String.class);
		assertEquals("Internal server error while accessing external apis", fromJson);

	}

}
