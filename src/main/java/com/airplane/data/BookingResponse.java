package com.airplane.data;

public class BookingResponse {
	private Booking booking;
	private ErrorData errorData;

	/**
	 * @param booking
	 * @param errorData
	 */
	public BookingResponse(Booking booking, ErrorData errorData) {
		this.booking = booking;
		this.errorData = errorData;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public ErrorData getErrorData() {
		return errorData;
	}

	public void setErrorData(ErrorData errorData) {
		this.errorData = errorData;
	}

	/**
	 * 
	 */
	public BookingResponse() {
	}
	
	

}
