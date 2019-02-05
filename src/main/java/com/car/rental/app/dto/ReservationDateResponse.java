package com.car.rental.app.dto;

import java.time.LocalDateTime;

public class ReservationDateResponse extends ReservationDate {

	private String reserved = "Selected Car Type is not available during this window.";
	
	public ReservationDateResponse(LocalDateTime reservationStartDateTime, Integer numberOfDays, String reserved) {
		this.setReservationStartDateTime(reservationStartDateTime);
		this.setNumberOfDays(numberOfDays);
		this.reserved = reserved;
	}

	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

}
