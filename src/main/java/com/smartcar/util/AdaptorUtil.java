package com.smartcar.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.smartcar.model.system.enums.ActionResponse;

/**
 * 
 * This class is used for adapting external system models to smart car response
 * model.
 * @author sanketsaurav 
 */
public class AdaptorUtil {

	private static final int FOUR_DOOR = 4;
	private static final int TWO_DOOR = 2;
	private static final String NULL_VALUE = "Null";
	private static final Logger LOGGER = LoggerFactory.getLogger(AdaptorUtil.class);

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
				doorCount = FOUR_DOOR;
			} else if (response.getTwoDoorCoupe().getValue().equalsIgnoreCase(Boolean.TRUE.toString())) {
				doorCount = TWO_DOOR;
			} else {
				LOGGER.warn("Unhandled scenario, setting door count to 0");
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
		if (!batteryLevel.getType().equals(NULL_VALUE))
			info.setPercent(Double.valueOf(batteryLevel.getValue()).intValue());
		else
			LOGGER.warn("Unhandled scenario, setting battery percent to 0");
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
		if (!tankLevel.getType().equals(NULL_VALUE))
			info.setPercent(Double.valueOf(tankLevel.getValue()).intValue());
		else
			LOGGER.warn("Unhandled scenario, setting fuel percent to 0");
		return info;
	}

	/**
	 * 
	 * @param action
	 * @return
	 */
	public static EngineActionCommands adaptToGmCommand(Action action) {
		if (Action.START.equals(action)) {
			return EngineActionCommands.START_VEHICLE;
		} else {
			return EngineActionCommands.STOP_VEHICLE;
		}
	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	public static EngineActionResponse adaptToEngineActionInfo(EngineActionResponseModel response) {
		EngineActionResponse result = new EngineActionResponse();
		if (response.getStatus().equals(EngineActionStatus.EXECUTED.toString()))
			result.setStatus(ActionResponse.SUCCESS.getValue());
		else
			result.setStatus(ActionResponse.ERROR.getValue());
		return result;
	}

}
