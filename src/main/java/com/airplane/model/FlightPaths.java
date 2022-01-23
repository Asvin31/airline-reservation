package com.airplane.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FlightPaths")
public class FlightPaths {
	@Id
	private String id;
	private String pathContext;
	private int pathId;
	private String name;

	public String getPathContext() {
		return pathContext;
	}

	public void setPathContext(String pathContext) {
		this.pathContext = pathContext;
	}

	public int getPathId() {
		return pathId;
	}

	public void setPathId(int pathId) {
		this.pathId = pathId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FlightPaths(String id, int pathId, String name, String pathContext) {
		this.id = id;
		this.pathId = pathId;
		this.name = name;
		this.pathContext = pathContext;
	}

}
