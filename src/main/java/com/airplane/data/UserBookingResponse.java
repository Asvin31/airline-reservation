package com.airplane.data;

import java.util.List;
import java.util.Map;

public class UserBookingResponse {
	private Map<String, List<Booking>> userBooking;
	private ErrorData errorData;

	public Map<String, List<Booking>> getUserBooking() {
		return userBooking;
	}

	public void setUserBooking(Map<String, List<Booking>> userBooking) {
		this.userBooking = userBooking;
	}

	public ErrorData getErrorData() {
		return errorData;
	}

	public void setErrorData(ErrorData errorData) {
		this.errorData = errorData;
	}

	public UserBookingResponse(Map<String, List<Booking>> userBooking, ErrorData errorData) {
		this.userBooking = userBooking;
		this.errorData = errorData;
	}

	public UserBookingResponse() {
	}

}
