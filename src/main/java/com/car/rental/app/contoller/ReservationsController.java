package com.car.rental.app.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.car.rental.app.dto.Reservation;
import com.car.rental.app.service.ReservationService;

@Controller
@RestController
public class ReservationsController {
	
	@Autowired ReservationService reservationService;

	@RequestMapping(value="/reserve", method=RequestMethod.POST)
	public Object rentCar(@RequestBody @Valid Reservation reservation) {
		return reservationService.makeReservations(reservation);
	}
	
}
