package com.car.rental.app.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

@Component
public class TotalBookingsByCarId {
	private static HashMap<Integer, SortedMap<LocalDateTime, LocalDateTime>> reservationsByCarId = new HashMap<Integer, SortedMap<LocalDateTime, LocalDateTime>>();
	private static HashMap<Integer, SortedMap<LocalDateTime, LocalDateTime>> freeTimeByCarId = new HashMap<Integer, SortedMap<LocalDateTime, LocalDateTime>>();

	public static void addReservation(Integer carId, LocalDateTime reservationStartDateTime, LocalDateTime reservationEndDateTime) {
		if(reservationsByCarId.containsKey(carId)) {
			reservationsByCarId.get(carId).put(reservationStartDateTime, reservationEndDateTime);
		} else {
			SortedMap<LocalDateTime, LocalDateTime> temp = new TreeMap<LocalDateTime, LocalDateTime>();
			temp.put(reservationStartDateTime, reservationEndDateTime);
			reservationsByCarId.put(carId, temp);
		}
	}
	
	public static void adjustFreeTime(Integer carId, LocalDateTime reservationStartDateTime, LocalDateTime reservationEndDateTime) {
		LocalDateTime freeTimeStartTemp = null;
		LocalDateTime freeTimeEndTemp = null;
		for(LocalDateTime freeTimeStart : freeTimeByCarId.get(carId).keySet()) {
			if(freeTimeStart.isBefore(reservationStartDateTime) &&
					freeTimeByCarId.get(carId).get(freeTimeStart).isAfter(reservationEndDateTime)) {
				freeTimeStartTemp = freeTimeStart;
				freeTimeEndTemp = freeTimeByCarId.get(carId).get(freeTimeStart);
				break;
			}
		}
		if(freeTimeStartTemp != null && freeTimeEndTemp != null) {
			freeTimeByCarId.get(carId).put(freeTimeStartTemp, reservationStartDateTime);
			freeTimeByCarId.get(carId).put(reservationEndDateTime, freeTimeEndTemp);
		}
	}
	
	public static SortedMap<LocalDateTime, LocalDateTime> getReservations(Integer carId) {
		return reservationsByCarId.get(carId);
	}
	
	public static SortedMap<LocalDateTime, LocalDateTime> getFreeTimesBycarId(Integer carId) {
		return freeTimeByCarId.get(carId);
	}
	
	public static HashMap<Integer, SortedMap<LocalDateTime, LocalDateTime>> getFreeTimesBycarId() {
		return freeTimeByCarId;
	}
	
}
