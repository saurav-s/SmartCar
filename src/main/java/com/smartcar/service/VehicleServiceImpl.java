package com.smartcar.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartcar.exception.ExceptionHandler;
import com.smartcar.exception.ExternalApiException;
import com.smartcar.model.external.gm.EngineActionResponseModel;
import com.smartcar.model.external.gm.FuelBatteryModel;
import com.smartcar.model.external.gm.SecurityStatusModel;
import com.smartcar.model.external.gm.VehicleInfoModel;
import com.smartcar.model.system.DoorSecurityInfo;
import com.smartcar.model.system.EngineActionResponse;
import com.smartcar.model.system.PercentInfo;
import com.smartcar.model.system.VehicleInfo;
import com.smartcar.model.system.enums.Action;
import com.smartcar.util.AdaptorUtil;
import com.smartcar.util.GMRestTemplateUtil;
import com.smartcar.util.JsonUtil;

/**
 * 
 * @author sanketsaurav
 *
 */
@Service
public class VehicleServiceImpl implements VehicleService{

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class); 
	/**
	 * 
	 * @param id
	 * @return vehicle info json
	 */
	public String getVehicleInfo(int id) {
		try {
			return JsonUtil.getJsonString(getVehicleInfoFromGm(id));
		} catch (ExternalApiException e) {
			LOGGER.error("Error occured in external api"+e.getMessage());
			return ExceptionHandler.generteResponse(e);
		} catch (Exception e) {
			LOGGER.error("Error occured : "+e.getMessage());
			return ExceptionHandler.generteResponse(e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return vehicle fuel info json
	 */
	public String getVehicleFuelInfo(int id) {
		try {
			return JsonUtil.getJsonString(getFuelInfoFromGm(id));
		} catch (ExternalApiException e) {
			LOGGER.error("Error occured in external api"+e.getMessage());
			return ExceptionHandler.generteResponse(e);
		} catch (Exception e) {
			LOGGER.error("Error occured : "+e.getMessage());
			return ExceptionHandler.generteResponse(e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getVehicleSecurityInfo(int id) {
		try {
			return JsonUtil.getJsonString(getVehicleSecurityInfoFromGm(id));
		} catch (ExternalApiException e) {
			LOGGER.error("Error occured in external api"+e.getMessage());
			return ExceptionHandler.generteResponse(e);
		} catch (Exception e) {
			LOGGER.error("Error occured : "+e.getMessage());
			return ExceptionHandler.generteResponse(e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getVehicleBatteryInfo(int id) {
		try {
			return JsonUtil.getJsonString(getBatteryInfoFromGm(id));
		} catch (ExternalApiException e) {
			LOGGER.error("Error occured in external api"+e.getMessage());
			return ExceptionHandler.generteResponse(e);
		} catch (Exception e) {
			LOGGER.error("Error occured : "+e.getMessage());
			return ExceptionHandler.generteResponse(e);
		}
	}

	/**
	 * 
	 * @param vehicleId
	 * @param action
	 * @return
	 */
	public String performEngineEaction(int id, Action action) {
		try {
			return JsonUtil.getJsonString(performEngineActionOnGm(id, action));
		} catch (ExternalApiException e) {
			LOGGER.error("Error occured in external api"+e.getMessage());
			return ExceptionHandler.generteResponse(e);
		} catch (Exception e) {
			LOGGER.error("Error occured : "+e.getMessage());
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
		return AdaptorUtil.adaptToDoorSecurityInfoList(response);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private VehicleInfo getVehicleInfoFromGm(int id) {
		VehicleInfoModel response = GMRestTemplateUtil.getVehicleInfoFromGmApi(id);
		return AdaptorUtil.adaptToVehicleInfo(response);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private PercentInfo getFuelInfoFromGm(int id) {
		FuelBatteryModel response = GMRestTemplateUtil.getFuelBatteryStatusFromGmApi(id);
		return AdaptorUtil.adaptToFuelPercentInfo(response);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private PercentInfo getBatteryInfoFromGm(int id) {
		FuelBatteryModel response = GMRestTemplateUtil.getFuelBatteryStatusFromGmApi(id);
		return AdaptorUtil.adaptToBatteryPercentInfo(response);
	}

	/**
	 * 
	 * @param id
	 * @param action
	 * @return
	 */
	private EngineActionResponse performEngineActionOnGm(int id, Action action) {
		EngineActionResponseModel response = GMRestTemplateUtil.performEngineActionOnGmApi(id,
				AdaptorUtil.adaptToGmCommand(action));
		return AdaptorUtil.adaptToEngineActionInfo(response);
	}

}
