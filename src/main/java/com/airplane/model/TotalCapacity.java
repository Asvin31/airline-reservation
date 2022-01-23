package com.airplane.model;

public class TotalCapacity {
	private int business;
	private int economic;

	public int getBusiness() {
		return business;
	}

	public void setBusiness(int business) {
		this.business = business;
	}

	public int getEconomic() {
		return economic;
	}

	public void setEconomic(int economic) {
		this.economic = economic;
	}

	public TotalCapacity(int business, int economic) {
		super();
		this.business = business;
		this.economic = economic;
	}

	public int getTotalCapacityDetails(String classType) {
		if (classType.equalsIgnoreCase("business")) {
			return business;
		} else {
			return economic;
		}
	}

}
