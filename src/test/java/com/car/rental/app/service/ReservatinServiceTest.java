package com.car.rental.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.car.rental.app.dto.CarType;
import com.car.rental.app.dto.Reservation;
import com.car.rental.app.dto.ReservationDate;
import com.car.rental.app.dto.ReservationResponse;
import com.car.rental.app.exception.ReservationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservatinServiceTest {
	
	@Autowired
	ReservationService reservationService;
	
	//assertRequiredFields should return true when required fields are missing.
	@Test
	public void assertFieldsTestWhenSomeFieldsAreNotPopulated() {
		Reservation reservation = new Reservation();
		reservation.setCarType(CarType.SMALL);
		reservation.setReservationDates(null);
		
		boolean value = reservationService.assertRequiredFields(reservation);
		assertTrue(value);
	}
	
	//assertRequiredFields should return true when required fields are missing.
	@Test
	public void assertFieldsTestWhenAllFieldsArePopulated() {
		Reservation reservation = new Reservation();
		reservation.setCarType(CarType.SMALL);
		ReservationDate reservationDate1 = new ReservationDate();
		reservationDate1.setReservationStartDateTime(LocalDateTime.now().plusDays(1));
		reservationDate1.setNumberOfDays(1);
		ArrayList<ReservationDate> reservationDates = new ArrayList<ReservationDate>();
		reservationDates.add(reservationDate1);
		reservation.setReservationDates(reservationDates);
		
		boolean value = reservationService.assertRequiredFields(reservation);
		assertFalse(value);
	}
	
	//Lets see what happens when time 2 reservations have overlapping time. checkTimeOverlaps method should return true.
	@Test
	public void checkTimeOverlapsTestWithOverlappingTimes() {
		Reservation reservation = new Reservation();
		reservation.setCarType(CarType.SMALL);
		reservation.setReservationDates(null);
		ReservationDate reservationDate1 = new ReservationDate();
		reservationDate1.setReservationStartDateTime(LocalDateTime.now().plusDays(1));
		reservationDate1.setNumberOfDays(1);
		
		ReservationDate reservationDate2 = new ReservationDate();
		reservationDate2.setReservationStartDateTime(LocalDateTime.now().plusDays(3));
		reservationDate2.setNumberOfDays(1);
		
		ReservationDate reservationDate3 = new ReservationDate();
		reservationDate3.setReservationStartDateTime(LocalDateTime.now().plusDays(1).plusMinutes(5)); //First reservation and this have same time with 5 mins difference.
		reservationDate3.setNumberOfDays(1);
		
		ArrayList<ReservationDate> reservationDates = new ArrayList<ReservationDate>();
		reservationDates.add(reservationDate1);
		reservationDates.add(reservationDate2);
		reservationDates.add(reservationDate3);
		reservation.setReservationDates(reservationDates);
		reservationService.setRervationEndDateTime(reservation);
		
		boolean value = reservationService.checkTimeOverlaps(reservation);
		assertTrue(value);
	}
	
	//Lets see what happens when all reservations have non overlapping time. checkTimeOverlaps method should return false.
	@Test
	public void checkTimeOverlapsTest() {
		Reservation reservation = new Reservation();
		reservation.setCarType(CarType.SMALL);
		reservation.setReservationDates(null);
		ReservationDate reservationDate1 = new ReservationDate();
		reservationDate1.setReservationStartDateTime(LocalDateTime.now().plusDays(1));
		reservationDate1.setNumberOfDays(1);
		
		ReservationDate reservationDate2 = new ReservationDate();
		reservationDate2.setReservationStartDateTime(LocalDateTime.now().plusDays(3));
		reservationDate2.setNumberOfDays(1);
		
		ReservationDate reservationDate3 = new ReservationDate();
		reservationDate3.setReservationStartDateTime(LocalDateTime.now().plusDays(5));
		reservationDate3.setNumberOfDays(1);
			
		ArrayList<ReservationDate> reservationDates = new ArrayList<ReservationDate>();
		reservationDates.add(reservationDate1);
		reservationDates.add(reservationDate2);
		reservationDates.add(reservationDate3);
		reservation.setReservationDates(reservationDates);
		reservationService.setRervationEndDateTime(reservation);
		
		boolean value = reservationService.checkTimeOverlaps(reservation);
		assertFalse(value);
	}
	
	@Test
	public void checkAvailabilityTest() {
		Reservation reservation = new Reservation();
		reservation.setCarType(CarType.SMALL);
		ReservationDate reservationDate = new ReservationDate();
		reservationDate.setReservationStartDateTime(LocalDateTime.now().plusDays(1));
		reservationDate.setNumberOfDays(1);
		ArrayList<ReservationDate> reservationDates = new ArrayList<ReservationDate>();
		reservationDates.add(reservationDate);
		reservation.setReservationDates(reservationDates);
		reservationService.setRervationEndDateTime(reservation);
		HashMap<ReservationDate, Integer> availablecars = reservationService.checkAvailability(reservation);
		assertNotNull(availablecars);
	}
	
	//Test case to check makeReservations method.
	@Test
	public void makeReservationTest() {
		Reservation reservation1 = new Reservation();
		reservation1.setCarType(CarType.SMALL);
		ReservationDate reservationDate = new ReservationDate();
		reservationDate.setReservationStartDateTime(LocalDateTime.now().plusDays(1));
		reservationDate.setNumberOfDays(1);
		ArrayList<ReservationDate> reservationDates = new ArrayList<ReservationDate>();
		reservationDates.add(reservationDate);
		reservation1.setReservationDates(reservationDates);
		//HashMap<ReservationDate, Integer> availablecars = reservationService.checkAvailability(reservation1);
		ReservationResponse reservationResponse = (ReservationResponse) reservationService.makeReservations(reservation1);
		assertNotNull(reservationResponse);
	}
	
	//Test case to check makeReservations method.
	@Test
	public void makeReservationTestWithAllRequiredFieldsNotPopulated() {
		Reservation reservation1 = new Reservation();
		reservation1.setCarType(CarType.SMALL);
		ReservationDate reservationDate = new ReservationDate();
		reservationDate.setReservationStartDateTime(LocalDateTime.now().plusDays(1));
		//reservationDate.setNumberOfDays(1);
		ArrayList<ReservationDate> reservationDates = new ArrayList<ReservationDate>();
		reservationDates.add(reservationDate);
		reservation1.setReservationDates(reservationDates);
		//HashMap<ReservationDate, Integer> availablecars = reservationService.checkAvailability(reservation1);
		ReservationException reservationException = (ReservationException) reservationService.makeReservations(reservation1);
		assertEquals(reservationException.getErrorId(), 4001);
	}
	
	//Test case to check makeReservations method when no cars available. At 6th reservation for same type of car and same time system should throw error.
//	@Test
//	public void makeReservationTestWhenAllCarsAreBookedForAGivenTime() {
//		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime formatDateTime = LocalDateTime.parse("2018-12-09 10:30", formatter);
//        
//		Reservation reservation1 = new Reservation();
//		reservation1.setCarType(CarType.SMALL);
//		ReservationDate reservationDate1 = new ReservationDate();
//		reservationDate1.setReservationStartDateTime(formatDateTime);
//		reservationDate1.setNumberOfDays(1);
//		ArrayList<ReservationDate> reservationDates1 = new ArrayList<ReservationDate>();
//		reservationDates1.add(reservationDate1);
//		reservation1.setReservationDates(reservationDates1);
//		ReservationResponse reservationResponse1 = (ReservationResponse) reservationService.makeReservations(reservation1);
//		
//		ReservationResponse reservationResponse2 = (ReservationResponse) reservationService.makeReservations(reservation1);
//		
//		ReservationResponse reservationResponse3 = (ReservationResponse) reservationService.makeReservations(reservation1);
//		
//		ReservationResponse reservationResponse4 = (ReservationResponse) reservationService.makeReservations(reservation1);
//		
//		ReservationResponse reservationResponse5 = (ReservationResponse) reservationService.makeReservations(reservation1);
//		
//		ReservationException reservationException = (ReservationException) reservationService.makeReservations(reservation1);
//		
//		assertEquals(reservationResponse1.getReservationDateResponses().get(0).getReserved(), "Reserved");
//		assertEquals(reservationResponse2.getReservationDateResponses().get(0).getReserved(), "Reserved");
//		assertEquals(reservationResponse3.getReservationDateResponses().get(0).getReserved(), "Reserved");
//		assertEquals(reservationResponse4.getReservationDateResponses().get(0).getReserved(), "Reserved");
//		assertEquals(reservationResponse5.getReservationDateResponses().get(0).getReserved(), "Reserved");
//		assertEquals(reservationException.getErrorId(), 4003);
//		
//	}

}
