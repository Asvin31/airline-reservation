package com.airplane.data;

public class BookRequestData {

	private int flightPath;
	private int flightId;
	private String typeOfClass;
	private int numberOfTickets;

	/**
	 * @return the flightPath
	 */
	public int getFlightPath() {
		return flightPath;
	}

	/**
	 * @param flightPath the flightPath to set
	 */
	public void setFlightPath(int flightPath) {
		this.flightPath = flightPath;
	}

	/**
	 * @return the flightId
	 */
	public int getFlightId() {
		return flightId;
	}

	/**
	 * @param flightId the flightId to set
	 */
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	/**
	 * @return the typeOfClass
	 */
	public String getTypeOfClass() {
		return typeOfClass;
	}

	/**
	 * @param typeOfClass the typeOfClass to set
	 */
	public void setTypeOfClass(String typeOfClass) {
		this.typeOfClass = typeOfClass;
	}

	/**
	 * @return the numberOfTickets
	 */
	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	/**
	 * @param numberOfTickets the numberOfTickets to set
	 */
	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	/**
	 * @param flightPath
	 * @param flightId
	 * @param typeOfClass
	 * @param numberOfTickets
	 */
	public BookRequestData(int flightPath, int flightId, String typeOfClass, int numberOfTickets) {
		this.flightPath = flightPath;
		this.flightId = flightId;
		this.typeOfClass = typeOfClass;
		this.numberOfTickets = numberOfTickets;
	}

}
