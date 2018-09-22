package com.smartcar.model.external.gm;

public class FuelBatteryModel {

	private TypeValuePair tankLevel;
	private TypeValuePair batteryLevel;

	public TypeValuePair getTankLevel() {
		return tankLevel;
	}

	public void setTankLevel(TypeValuePair tankLevel) {
		this.tankLevel = tankLevel;
	}

	public TypeValuePair getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(TypeValuePair batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

}
