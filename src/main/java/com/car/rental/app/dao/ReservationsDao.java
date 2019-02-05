package com.car.rental.app.dao;

import java.time.LocalDateTime;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.car.rental.app.dto.TotalBookingsByCarId;

@Component
public class ReservationsDao {
	
	public SortedMap<LocalDateTime, LocalDateTime> getReservationsByCarIdAndDateRange(Integer carId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime) {
		SortedMap<LocalDateTime, LocalDateTime> dateTimes = new TreeMap<LocalDateTime, LocalDateTime>();
		for(LocalDateTime dateTime : TotalBookingsByCarId.getReservations(carId).keySet()) {
			if(dateTime.isAfter(reservationStartTime) && dateTime.isBefore(reservationEndTime)) {
				dateTimes.put(dateTime, TotalBookingsByCarId.getReservations(carId).get(dateTime));
			}
		}
		return dateTimes;
	}
	
	public SortedMap<LocalDateTime, LocalDateTime> getFreeTimeByCarIdAndDateRange(Integer carId, LocalDateTime reservationStartTime, LocalDateTime reservationEndTime) {
		SortedMap<LocalDateTime, LocalDateTime> dateTimes = new TreeMap<LocalDateTime, LocalDateTime>();
		for(LocalDateTime dateTime : TotalBookingsByCarId.getFreeTimesBycarId(carId).keySet()) {
			if(dateTime.isBefore(reservationStartTime) && TotalBookingsByCarId.getFreeTimesBycarId(carId).get(dateTime).isAfter(reservationEndTime)) {
				dateTimes.put(dateTime, TotalBookingsByCarId.getFreeTimesBycarId(carId).get(dateTime));
			}
		}
		return dateTimes;
	}

}
