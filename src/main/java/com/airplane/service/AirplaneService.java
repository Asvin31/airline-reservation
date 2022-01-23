package com.airplane.service;

import com.airplane.data.AllBookingResponse;
import com.airplane.data.BookRequestData;
import com.airplane.data.BookingResponse;
import com.airplane.data.ChangeBookingRequest;
import com.airplane.data.UserBookingResponse;

public interface AirplaneService {
	BookingResponse bookFlight(BookRequestData bookRequestData);

	BookingResponse changeStatus(ChangeBookingRequest changeBookingRequest);

	UserBookingResponse getUsersBookings(String userId);

	AllBookingResponse allBookings();
}
