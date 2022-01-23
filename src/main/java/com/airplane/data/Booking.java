package com.airplane.data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Booking")
public class Booking {
	@Transient
	public static final String SEQUENCE_NAME = "booking_sequence";
	@Id
	private String bookingId;
	private int flightId;
	private String typeOfClass;
	private int numberOfTickets;
	private double ticketPrice;
	private double tax;
	private double bookingCharge;
	private double total;
	private String bookingStatus;
	private int flightPath;
	private String flightName;
	private String userId;
	private LocalDateTime bookingTime;
	private LocalDateTime lastModified;

	public int getFlightPath() {
		return flightPath;
	}

	public void setFlightPath(int flightPath) {
		this.flightPath = flightPath;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getTypeOfClass() {
		return typeOfClass;
	}

	public void setTypeOfClass(String typeOfClass) {
		this.typeOfClass = typeOfClass;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getBookingCharge() {
		return bookingCharge;
	}

	public void setBookingCharge(double bookingCharge) {
		this.bookingCharge = bookingCharge;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public Booking() {
	}

	public Booking(int flightId, String typeOfClass, int numberOfTickets, double ticketPrice, double tax,
			double bookingCharge, double total, String bookingStatus, int flightPath, String flightName, String userId,
			LocalDateTime bookingTime, LocalDateTime lastModified) {
		this.flightId = flightId;
		this.typeOfClass = typeOfClass;
		this.numberOfTickets = numberOfTickets;
		this.ticketPrice = ticketPrice;
		this.tax = tax;
		this.bookingCharge = bookingCharge;
		this.total = total;
		this.bookingStatus = bookingStatus;
		this.flightPath = flightPath;
		this.flightName = flightName;
		this.userId = userId;
		this.bookingTime = bookingTime;
		this.lastModified = lastModified;
	}

	public Booking(String bookingId, int flightId, String typeOfClass, int numberOfTickets, double ticketPrice,
			double tax, double bookingCharge, double total, String bookingStatus, int flightPath, String flightName,
			String userId, LocalDateTime bookingTime, LocalDateTime lastModified) {
		this.bookingId = bookingId;
		this.flightId = flightId;
		this.typeOfClass = typeOfClass;
		this.numberOfTickets = numberOfTickets;
		this.ticketPrice = ticketPrice;
		this.tax = tax;
		this.bookingCharge = bookingCharge;
		this.total = total;
		this.bookingStatus = bookingStatus;
		this.flightPath = flightPath;
		this.flightName = flightName;
		this.userId = userId;
		this.bookingTime = bookingTime;
		this.lastModified = lastModified;
	}

}
