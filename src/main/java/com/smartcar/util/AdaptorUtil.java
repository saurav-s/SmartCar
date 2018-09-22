package com.smartcar.util;

import java.util.ArrayList;
import java.util.List;

import com.smartcar.model.external.gm.DoorSecurityModel;
import com.smartcar.model.external.gm.DoorSecurityModels;
import com.smartcar.model.external.gm.EngineActionResponseModel;
import com.smartcar.model.external.gm.FuelBatteryModel;
import com.smartcar.model.external.gm.SecurityStatusModel;
import com.smartcar.model.external.gm.TypeValuePair;
import com.smartcar.model.external.gm.VehicleInfoModel;
import com.smartcar.model.external.gm.enums.EngineActionCommands;
import com.smartcar.model.external.gm.enums.EngineActionStatus;
import com.smartcar.model.system.DoorSecurityInfo;
import com.smartcar.model.system.EngineActionResponse;
import com.smartcar.model.system.PercentInfo;
import com.smartcar.model.system.VehicleInfo;
import com.smartcar.model.system.enums.Action;

public class AdaptorUtil {

	/**
	 * 
	 * @param response
	 * @return
	 */
	public static VehicleInfo adaptToVehicleInfo(VehicleInfoModel response) {
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
	 * @param response
	 * @return
	 */
	public static List<DoorSecurityInfo> adaptToDoorSecurityInfoList(SecurityStatusModel response) {
		List<DoorSecurityInfo> doorSecurityList = new ArrayList<>();
		DoorSecurityModels doors = response.getDoors();
		List<DoorSecurityModel> doorInfo = doors.getValues();
		for (DoorSecurityModel door : doorInfo) {
			TypeValuePair location = door.getLocation();
			TypeValuePair locked = door.getLocked();
			DoorSecurityInfo doorSecurityResponse = new DoorSecurityInfo();
			doorSecurityResponse.setLocation(location.getValue());
			doorSecurityResponse.setLocked(Boolean.valueOf(locked.getValue()));
			doorSecurityList.add(doorSecurityResponse);
		}
		return doorSecurityList;
	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	public static PercentInfo adaptToBatteryPercentInfo(FuelBatteryModel response) {
		PercentInfo info = new PercentInfo();
		TypeValuePair batteryLevel = response.getBatteryLevel();
		if (!batteryLevel.getType().equals("Null"))
			info.setPercent(Double.valueOf(batteryLevel.getValue()).intValue());
		return info;
	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	public static PercentInfo adaptToFuelPercentInfo(FuelBatteryModel response) {
		PercentInfo info = new PercentInfo();
		TypeValuePair tankLevel = response.getTankLevel();
		if (!tankLevel.getType().equals("Null"))
			info.setPercent(Double.valueOf(tankLevel.getValue()).intValue());
		return info;
	}
	
	/**
	 * 
	 * @param action
	 * @return
	 */
	public static EngineActionCommands adaptToGmCommand(Action action) {
		if(Action.START.equals(action)) 
			return EngineActionCommands.START_VEHICLE;
		else
			return EngineActionCommands.STOP_VEHICLE;
	}

	
	/**
	 * 
	 * @param response
	 * @return
	 */
	public static EngineActionResponse adaptToEngineActionInfo(EngineActionResponseModel response) {
		EngineActionResponse result = new EngineActionResponse();
		if(response.getStatus().equals(EngineActionStatus.EXECUTED.toString()))
			result.setStatus("success");
		else
			result.setStatus("error");
		return result;
	}

}
