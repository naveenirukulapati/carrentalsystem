package com.car.rental.app.dto;

import java.util.List;

public class ReservationResponse {

	private CarType carType;
	private List<ReservationDateResponse> ReservationDateResponses;
	private List<Ord> orders;
	
	public CarType getCarType() {
		return carType;
	}
	public void setCarType(CarType carType) {
		this.carType = carType;
	}
	public List<ReservationDateResponse> getReservationDateResponses() {
		return ReservationDateResponses;
	}
	public void setReservationDateResponses(List<ReservationDateResponse> reservationDateResponses) {
		ReservationDateResponses = reservationDateResponses;
	}
	public List<Ord> getOrders() {
		return orders;
	}
	public void setOrders(List<Ord> orders) {
		this.orders = orders;
	}
}
