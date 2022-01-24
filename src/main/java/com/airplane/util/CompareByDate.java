package com.airplane.util;

import java.util.Comparator;

import com.airplane.data.Booking;

public class CompareByDate implements Comparator<Booking> {

	@Override
	public int compare(Booking b1, Booking b2) {
		return (b1.getBookingTime() == null || b2.getBookingTime() == null) ? 0
				: b1.getBookingTime().compareTo(b2.getBookingTime());
	}

}
