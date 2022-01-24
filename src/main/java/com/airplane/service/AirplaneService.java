package com.airplane.service;

import java.util.Arrays;
import java.util.List;

import com.airplane.data.AllBookingResponse;
import com.airplane.data.BookRequestData;
import com.airplane.data.BookingResponse;
import com.airplane.data.ChangeBookingRequest;
import com.airplane.data.ErrorData;
import com.airplane.data.UserBookingResponse;

public interface AirplaneService {

	final List<String> MOBILEDEVICES = Arrays.asList("Android", "iPhone", "iPad");

	final BookingResponse notSupportedResponse = new BookingResponse(null, new ErrorData(500, "Not Supported"));

	final AllBookingResponse allBookingResponse = new AllBookingResponse(null, new ErrorData(500, "Not Supported"));

	BookingResponse bookFlight(BookRequestData bookRequestData);

	BookingResponse changeStatus(ChangeBookingRequest changeBookingRequest);

	UserBookingResponse getUsersBookings(String userId);

	AllBookingResponse allBookings();

	default Boolean checkUserAgent(String userAgent) {
		return MOBILEDEVICES.stream().anyMatch(userAgent::contains);
	}

	default BookingResponse returnNotSupportedResponse() {
		return notSupportedResponse;
	}

	default AllBookingResponse returnNotSupportedForAllBookings() {
		return allBookingResponse;
	}
}
