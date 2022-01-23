package com.airplane.model;

public class Price {

	private double business;
	private double economic;

	public double getBusiness() {
		return business;
	}

	public void setBusiness(double business) {
		this.business = business;
	}

	public double getEconomic() {
		return economic;
	}

	public void setEconomic(double economic) {
		this.economic = economic;
	}

	public Price(double business, double economic) {
		super();
		this.business = business;
		this.economic = economic;
	}

	public double getPriceDetails(String classType) {
		if (classType.equalsIgnoreCase("business")) {
			return business;
		} else {
			return economic;
		}
	}

}
