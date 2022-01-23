package com.airplane.data;

public class ErrorData {
	private int statusCode;
	private String errorDetails;

	/**
	 * @param statusCode
	 * @param errorDetails
	 */
	public ErrorData(int statusCode, String errorDetails) {
		this.statusCode = statusCode;
		this.errorDetails = errorDetails;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

}
