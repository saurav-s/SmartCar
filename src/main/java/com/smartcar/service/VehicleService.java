package com.smartcar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartcar.exception.ExceptionHandler;
import com.smartcar.model.external.gm.DoorSecurityModel;
import com.smartcar.model.external.gm.DoorSecurityModels;
import com.smartcar.model.external.gm.SecurityStatusModel;
import com.smartcar.model.external.gm.TypeValuePair;
import com.smartcar.model.external.gm.VehicleInfoModel;
import com.smartcar.model.system.DoorSecurityInfo;
import com.smartcar.model.system.VehicleInfo;
import com.smartcar.util.GMRestTemplateUtil;
import com.smartcar.util.JsonUtil;


/**
 * 
 * @author sanketsaurav
 *
 */
@Service
public class VehicleService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getVehicleInfo(int id) {
		try {
			return JsonUtil.getJsonString(getVehicleInfoFromGm(id));
		}catch (Exception e) {
			return ExceptionHandler.generteResponse(e);
		}
	}



	/**
	 * 
	 * @param id
	 * @return
	 */
	public VehicleInfo getVehicleInfoFromGm(int id) {
		VehicleInfoModel response = GMRestTemplateUtil.getVehicleInfoFromGmApi(id);
		return adaptToVehicleInfo(response);
	}


	/**
	 * 
	 * @param response
	 * @return
	 */
	private VehicleInfo adaptToVehicleInfo(VehicleInfoModel response) {
		VehicleInfo info = new VehicleInfo();
		if (response != null) {

			info.setVin(response.getVin().getValue());
			info.setColor(response.getColor().getValue());
			info.setDriveTrain(response.getDriveTrain().getValue());
			int doorCount = 0;
			if (response.getFourDoorSedan().getValue().equalsIgnoreCase(Boolean.TRUE.toString())) {
				doorCount = 4;
			} else if (response.getTwoDoorCoupe().getValue().equalsIgnoreCase(Boolean.TRUE.toString())) {
				doorCount = 2;
			}
			info.setDoorCount(doorCount);
		}
		return info;
	}


	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getVehicleSecurityInfo(int id) {
		try {
			return JsonUtil.getJsonString(getVehicleSecurityInfoFromGm(id));
		}catch (Exception e) {
			return ExceptionHandler.generteResponse(e);
		}
	}


	/**
	 * 
	 * @param id
	 * @return
	 */
	private List<DoorSecurityInfo> getVehicleSecurityInfoFromGm(int id) {
		SecurityStatusModel response = GMRestTemplateUtil.getSecurityStatusFromGmApi(id);
		return adaptToDoorSecurityInfoList(response);
	}



	private List<DoorSecurityInfo> adaptToDoorSecurityInfoList(SecurityStatusModel response) {
		List<DoorSecurityInfo> doorSecurityList = new ArrayList<>();
		DoorSecurityModels doors = response.getDoors();
		List<DoorSecurityModel> doorInfo = doors.getValues();
		for(DoorSecurityModel door:doorInfo) {
			TypeValuePair location = door.getLocation();
			TypeValuePair locked = door.getLocked();
			DoorSecurityInfo doorSecurityResponse = new DoorSecurityInfo();
			doorSecurityResponse.setLocation(location.getValue());
			doorSecurityResponse.setLocked(Boolean.valueOf(locked.getValue()));
			doorSecurityList.add(doorSecurityResponse);
		}
		return doorSecurityList;
	}

}
