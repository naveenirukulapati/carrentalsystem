Assumptions:
1. User/Customer is not used
2. Not adding functionality for returning the car
3. When multiple reservations made through one request there is no guarantee same car will be available for each reservation
4. No Authentication added
6. Single request with multiple reservations, times should not overlap
7. Each reservation request is one transaction

Note:
1. Implemented using Java 1.8, Spring Boot and Spring Web
2. Reservation end point: http://localhost:9096/carrentalsystem/reserve
3. Request body
{
	"carType": "MEDIUM",
	"reservationDates": [
		{
			"reservationStartDateTime": "2018-12-10 08:00",
			"numberOfDays": 2
		},
		{
			"reservationStartDateTime": "2018-12-15 08:00",
			"numberOfDays": 2
		}
	]
}

4. Used HashMaps, TreeMap and ArrayList for storing data instead of database. Used this approach because of lack of DB.
5. Algorithm will calculate the free available time slots while making a reservation.
6. Written test cases for some positive and negative scenarios. But in reality I should write more, didn't complete all of them because of time constraint.

Considerations:
1. Tried to follow MVC pattern
2. Implemented small methods so that it will be easy to unit test those methods
3. Tried not to repeat the code

Algorithm
Step1: get input - one request can have multiple reservations for same type of car
Step2: validate input message - check all fields are populated
Step3: check input message for any over lapping reservations. If so throw error.
Step4: check cars are available for the requested times. if not return response.
Step5: make reservations and respond.