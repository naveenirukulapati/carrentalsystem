package com.car.rental.app.dto;

public enum CarType {

	SMALL("SMALL"),
	MEDIUM("MEDIUM"),
	LARGE("LARGE"),
	SUV("SUV"),
	VAN("VAN");
	
	private String carType;
	
	public String getcarType() {
		return this.carType;
	}
	
	private CarType(String carType) {
		this.carType = carType;
	}
}
