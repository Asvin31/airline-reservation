package com.airplane.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Flights")
public class Flights {

	@Id
	private String id;
	private int flightId;
	private int flightPath;
	private String flightName;
	private TotalCapacity totalCapacity;
	private Available available;
	private Price price;

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getflightPath() {
		return flightPath;
	}

	public void setflightPath(int flightPath) {
		this.flightPath = flightPath;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public TotalCapacity getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(TotalCapacity totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public Available getAvailable() {
		return available;
	}

	public void setAvailable(Available available) {
		this.available = available;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Flights(String id, int flightId, int flightPath, String flightName, TotalCapacity totalCapacity,
			Available available, Price price) {
		super();
		this.id = id;
		this.flightId = flightId;
		this.flightPath = flightPath;
		this.flightName = flightName;
		this.totalCapacity = totalCapacity;
		this.available = available;
		this.price = price;
	}

}
