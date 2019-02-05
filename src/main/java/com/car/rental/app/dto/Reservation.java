package com.car.rental.app.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class Reservation {
	
	@NotNull
	private CarType carType;
	
	private List<ReservationDate> reservationDates;
	
	public CarType getCarType() {
		return carType;
	}
	public void setCarType(CarType carType) {
		this.carType = carType;
	}
	public List<ReservationDate> getReservationDates() {
		return reservationDates;
	}
	public void setReservationDates(List<ReservationDate> reservationDates) {
		this.reservationDates = reservationDates;
	}

}
