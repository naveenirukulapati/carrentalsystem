package com.car.rental.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rental.app.dao.ReservationsDao;
import com.car.rental.app.dto.Car;
import com.car.rental.app.dto.CarType;
import com.car.rental.app.dto.Ord;
import com.car.rental.app.dto.Reservation;
import com.car.rental.app.dto.ReservationDate;
import com.car.rental.app.dto.ReservationDateResponse;
import com.car.rental.app.dto.ReservationResponse;
import com.car.rental.app.dto.TotalBookingsByCarId;
import com.car.rental.app.dto.TotalCars;
import com.car.rental.app.exception.ReservationException;
import com.car.rental.app.repository.CarRentalSystemRepository;

@Service
public class ReservationService {
	
	@Autowired ReservationsDao reservationsDao;
	
	//@Autowired CarRentalSystemRepository carRentalSystemRepository;

	public Object makeReservations(Reservation reservation) {
		//Validate all required fields for nulls
		if(assertRequiredFields(reservation)) {
			return new ReservationException(4001, "InputRequestValidationError", "All required fields are not populated in the input request message.");
		}
		//Calculate reservation end datetime.
		setRervationEndDateTime(reservation);
		//Validate all required fields for nulls
		if(checkTimeOverlaps(reservation)) {
			return new ReservationException(4002, "TimeOverlappingBetweenReservations", "Please provide non overlapping times for each reservation.");
		}
		//Check if car is available for each reservation
		HashMap<ReservationDate, Integer> availableCarByReservation = checkAvailability(reservation);
		if(availableCarByReservation == null) {
			return new ReservationException(4003, "NotAvailableError", "Requested car is not available at the requested time.");
		}
		//Make reservation
		return makeReservation(availableCarByReservation, reservation.getCarType());
	}
	
	public boolean assertRequiredFields(Reservation reservation) {
		//Check for all required fields are available or not
		if(reservation.getCarType() == null) return true;
		if(reservation.getReservationDates() == null || reservation.getReservationDates().isEmpty()) return true;
		for(ReservationDate reservationDate : reservation.getReservationDates()) {
			if(reservationDate.getReservationStartDateTime() == null) return true;
			if(reservationDate.getNumberOfDays() == null) return true;
		}
		return false;
	}
	
	public boolean checkTimeOverlaps(Reservation reservation) {
		for(int i=0;i<reservation.getReservationDates().size();i++) {
			for(int j=0;j<reservation.getReservationDates().size() && i!=j;j++) {
				if(isOverlapping(reservation.getReservationDates().get(j), reservation.getReservationDates().get(i)))
					return true;
			}
		}
		return false;
	}
	
	public boolean isOverlapping(ReservationDate firstReservationDate, ReservationDate secondReservationDate) {
		return (firstReservationDate.getReservationStartDateTime().isBefore(secondReservationDate.getReservationEndDateTime()) 
				//|| secondReservationDate.getReservationStartDateTime().isAfter(firstReservationDate.getReservationStartDateTime())
				)
				&& (secondReservationDate.getReservationStartDateTime().isBefore(firstReservationDate.getReservationEndDateTime())
				//|| secondReservationDate.getReservationEndDateTime().isBefore(firstReservationDate.getReservationEndDateTime())
				);
	}
	
	public void setRervationEndDateTime(Reservation reservation) {
		for(ReservationDate reservationDate : reservation.getReservationDates()) {
			reservationDate.setReservationEndDateTime(reservationDate.getReservationStartDateTime().plusDays(reservationDate.getNumberOfDays()));
		}
	}
	
	public HashMap<ReservationDate, Integer> checkAvailability(Reservation reservation) {
		HashMap<ReservationDate, Integer> availableCarByReservation = new HashMap<ReservationDate, Integer>();
		for(ReservationDate reservationDate : reservation.getReservationDates()) {
			Integer availableCarId = checkAvailabilityForEachReservation(reservationDate, reservation.getCarType());
			if(availableCarId !=0) {
				availableCarByReservation.put(reservationDate, availableCarId);
			} else {
				return null;
			}
		}
		return availableCarByReservation;
	}
	
	public ReservationResponse makeReservation(HashMap<ReservationDate, Integer> availableCarByReservation, CarType carType) {
		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setCarType(carType);
		for(ReservationDate reservationDate : availableCarByReservation.keySet()) {
			ReservationDateResponse reservationDateResponse = new ReservationDateResponse(reservationDate.getReservationStartDateTime(), reservationDate.getNumberOfDays(), "Reserved");
			if(reservationResponse.getReservationDateResponses() == null) {
				reservationResponse.setReservationDateResponses(new ArrayList<ReservationDateResponse>());
			}
			reservationResponse.getReservationDateResponses().add(reservationDateResponse);
			TotalBookingsByCarId.addReservation(availableCarByReservation.get(reservationDate), reservationDate.getReservationStartDateTime(), reservationDate.getReservationEndDateTime());
			TotalBookingsByCarId.adjustFreeTime(availableCarByReservation.get(reservationDate), reservationDate.getReservationStartDateTime(), reservationDate.getReservationEndDateTime());
		}
		//reservationResponse.setOrders(findAll());
		return reservationResponse;
	}
	
	public Integer checkAvailabilityForEachReservation(ReservationDate reservationDate, CarType carType) {
		//Check availability by each Car
		for(Car car : TotalCars.getTotalCars().get(carType)) {
			SortedMap<LocalDateTime, LocalDateTime> freeTimes = reservationsDao.getFreeTimeByCarIdAndDateRange(car.getId(), reservationDate.getReservationStartDateTime(), reservationDate.getReservationEndDateTime());
			if(!freeTimes.isEmpty() && freeTimes.firstKey().isBefore(reservationDate.getReservationStartDateTime())
						&& freeTimes.get(freeTimes.firstKey()).isAfter(reservationDate.getReservationEndDateTime())) {
				//free time available
				return car.getId();
			}
		}
		return 0;
	}
	
    /*public List<Ord> findAll() {

        List<Ord> cities = (List<Ord>) carRentalSystemRepository.findAll();
        
        return cities;
    }*/
	
}
