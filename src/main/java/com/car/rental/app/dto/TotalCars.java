package com.car.rental.app.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class TotalCars {

	private static HashMap<CarType, ArrayList<Car>> totalCars = new HashMap<CarType, ArrayList<Car>>();
	
	@PostConstruct
	public void loadCars() {
		
		ArrayList<Car> smallCarsList = new ArrayList<Car>();
		smallCarsList.add(new Car(1, CarType.SMALL));
		smallCarsList.add(new Car(2, CarType.SMALL));
		smallCarsList.add(new Car(3, CarType.SMALL));
		smallCarsList.add(new Car(4, CarType.SMALL));
		smallCarsList.add(new Car(5, CarType.SMALL));
		totalCars.put(CarType.SMALL, smallCarsList);
		
		ArrayList<Car> mediumCarsList = new ArrayList<Car>();
		mediumCarsList.add(new Car(6, CarType.MEDIUM));
		mediumCarsList.add(new Car(7, CarType.MEDIUM));
		mediumCarsList.add(new Car(8, CarType.MEDIUM));
		mediumCarsList.add(new Car(9, CarType.MEDIUM));
		mediumCarsList.add(new Car(10, CarType.MEDIUM));
		totalCars.put(CarType.MEDIUM, mediumCarsList);
		
		ArrayList<Car> largeCarsList = new ArrayList<Car>();
		largeCarsList.add(new Car(11, CarType.LARGE));
		largeCarsList.add(new Car(12, CarType.LARGE));
		largeCarsList.add(new Car(13, CarType.LARGE));
		largeCarsList.add(new Car(14, CarType.LARGE));
		largeCarsList.add(new Car(15, CarType.LARGE));
		totalCars.put(CarType.LARGE, largeCarsList);
		
		ArrayList<Car> suvCarsList = new ArrayList<Car>();
		suvCarsList.add(new Car(16, CarType.SUV));
		suvCarsList.add(new Car(17, CarType.SUV));
		suvCarsList.add(new Car(18, CarType.SUV));
		suvCarsList.add(new Car(19, CarType.SUV));
		suvCarsList.add(new Car(20, CarType.SUV));
		totalCars.put(CarType.SUV, suvCarsList);
		
		ArrayList<Car> vansCarsList = new ArrayList<Car>();
		vansCarsList.add(new Car(21, CarType.VAN));
		vansCarsList.add(new Car(22, CarType.VAN));
		vansCarsList.add(new Car(23, CarType.VAN));
		vansCarsList.add(new Car(24, CarType.VAN));
		vansCarsList.add(new Car(25, CarType.VAN));
		totalCars.put(CarType.VAN, vansCarsList);
		
		
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.now().plusDays(600);
		
		for(CarType carType : CarType.values()) {
			for(Car car : TotalCars.getTotalCars().get(carType)) {
				SortedMap<LocalDateTime, LocalDateTime> freeTime = new TreeMap<LocalDateTime, LocalDateTime>();
				freeTime.put(startTime, endTime);
				TotalBookingsByCarId.getFreeTimesBycarId().put(car.getId(), freeTime);
			}
		}
		
	}

	public static HashMap<CarType, ArrayList<Car>> getTotalCars() {
		return totalCars;
	}
	
}
