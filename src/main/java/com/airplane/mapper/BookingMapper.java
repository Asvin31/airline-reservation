package com.airplane.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.airplane.data.BookRequestData;
import com.airplane.data.Booking;

@Component
public class BookingMapper {

	public Booking convertRequestToBooking(BookRequestData bookRequestData) {
		Booking booking = new Booking();
		booking.setTypeOfClass(bookRequestData.getTypeOfClass());
		booking.setFlightId(bookRequestData.getFlightId());
		booking.setFlightPath(bookRequestData.getFlightPath());
		booking.setNumberOfTickets(bookRequestData.getNumberOfTickets());
		booking.setUserId("user");
		booking.setBookingTime(LocalDateTime.now());
		booking.setLastModified(LocalDateTime.now());
		return booking;
	}

}
