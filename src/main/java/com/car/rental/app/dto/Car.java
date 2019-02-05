package com.car.rental.app.dto;

public class Car {
	
	private Integer id;
	private CarType carType;
	
	public Car(Integer id, CarType carType) {
		this.id = id;
		this.carType = carType;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CarType getCarType() {
		return carType;
	}
	public void setCarType(CarType carType) {
		this.carType = carType;
	}

}
