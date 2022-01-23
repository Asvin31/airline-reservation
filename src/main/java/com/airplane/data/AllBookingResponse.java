package com.airplane.data;

import java.util.List;
import java.util.Map;

public class AllBookingResponse {
	private Map<String, Map<String, List<Booking>>> allBookings;
	private ErrorData errorData;

	public AllBookingResponse(Map<String, Map<String, List<Booking>>> allBookings, ErrorData errorData) {
		this.allBookings = allBookings;
		this.errorData = errorData;
	}

	public AllBookingResponse() {
	}

	public Map<String, Map<String, List<Booking>>> getAllBookings() {
		return allBookings;
	}

	public void setAllBookings(Map<String, Map<String, List<Booking>>> allBookings) {
		this.allBookings = allBookings;
	}

	public ErrorData getErrorData() {
		return errorData;
	}

	public void setErrorData(ErrorData errorData) {
		this.errorData = errorData;
	}

}
