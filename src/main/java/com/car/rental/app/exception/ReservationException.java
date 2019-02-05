package com.car.rental.app.exception;

public class ReservationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int errorId;
	private String errorType;
	private String errorDescription;
	
	public ReservationException(int errorId, String errorType, String errorDescription) {
		this.errorId = errorId;
		this.errorType = errorType;
		this.errorDescription = errorDescription;
	}
	
	public int getErrorId() {
		return errorId;
	}
	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

}
