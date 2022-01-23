package com.airplane.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airplane.data.AllBookingResponse;
import com.airplane.data.BookRequestData;
import com.airplane.data.BookingResponse;
import com.airplane.data.ChangeBookingRequest;
import com.airplane.data.UserBookingResponse;
import com.airplane.model.FlightPaths;
import com.airplane.model.Flights;
import com.airplane.repository.BookingRepo;
import com.airplane.repository.FlightPathRepo;
import com.airplane.repository.FlightRepo;
import com.airplane.service.AirplaneService;

/**
 * @author Asvin
 *
 */
@RestController
@RequestMapping("/api/airplane")
public class AirplaneController {

	@Autowired
	AirplaneService airplaneService;

	@Autowired
	FlightPathRepo flightPathRepo;

	@Autowired
	FlightRepo flightRepo;

	@Autowired
	BookingRepo bookingRepo;

	/**
	 * @return available Flight routes
	 */
	@GetMapping(value = "/getFlightPaths")
	public List<FlightPaths> getFlightPaths() {
		List<FlightPaths> flightPaths = flightPathRepo.findAll();
		flightPaths.sort(Comparator.comparing(FlightPaths::getName));
		return flightPaths;
	}

	/**
	 * @param flightPath
	 * @return List of Flights available for the route
	 */
	@GetMapping(value = "/getFlights")
	public List<Flights> getFlights(@RequestParam @Validated @NonNull int flightPath) {
		return flightRepo.findByFlightPath(flightPath);

	}

	/**
	 * @param bookRequestData
	 * @return
	 */
	@PostMapping(value = "/book")
	public ResponseEntity<BookingResponse> bookFlight(@Validated @RequestBody BookRequestData bookRequestData) {
		int statusCode = 200;
		BookingResponse bookingResponse = airplaneService.bookFlight(bookRequestData);
		if (!Objects.isNull(bookingResponse.getErrorData())) {
			statusCode = bookingResponse.getErrorData().getStatusCode();
		}
		return ResponseEntity.status(statusCode).body(bookingResponse);
	}

	@PostMapping(value = "/changeStatus")
	public ResponseEntity<BookingResponse> changeStatus(@RequestBody ChangeBookingRequest changeBookingRequest) {
		int statusCode = 200;
		BookingResponse bookingResponse = airplaneService.changeStatus(changeBookingRequest);
		if (!Objects.isNull(bookingResponse.getErrorData())) {
			statusCode = bookingResponse.getErrorData().getStatusCode();
		}
		return ResponseEntity.status(statusCode).body(bookingResponse);

	}

	@GetMapping(value = "/getBookings")
	public ResponseEntity<UserBookingResponse> getBookings(@RequestParam @Validated @NonNull String userId) {
		int statusCode = 200;
		UserBookingResponse userBookingResponse = airplaneService.getUsersBookings(userId);
		if (!Objects.isNull(userBookingResponse.getErrorData())) {
			statusCode = userBookingResponse.getErrorData().getStatusCode();
		}
		return ResponseEntity.status(statusCode).body(userBookingResponse);
	}
	
	@GetMapping(value = "/getAllBookings")
	public ResponseEntity<AllBookingResponse> getAllBookings() {
		int statusCode = 200;
		AllBookingResponse allBookingResponse = airplaneService.allBookings();
		if (!Objects.isNull(allBookingResponse.getErrorData())) {
			statusCode = allBookingResponse.getErrorData().getStatusCode();
		}
		return ResponseEntity.status(statusCode).body(allBookingResponse);
	}

}
