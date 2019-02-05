package com.car.rental.app.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class ReservationDate {

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime reservationStartDateTime;
	
	@NotNull
	private Integer numberOfDays;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime reservationEndDateTime;
	
	public LocalDateTime getReservationStartDateTime() {
		return reservationStartDateTime;
	}
	public void setReservationStartDateTime(LocalDateTime reservationStartDateTime) {
		this.reservationStartDateTime = reservationStartDateTime;
	}
	public Integer getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public LocalDateTime getReservationEndDateTime() {
		return reservationEndDateTime;
	}
	public void setReservationEndDateTime(LocalDateTime reservationEndDateTime) {
		this.reservationEndDateTime = reservationEndDateTime;
	}
}
