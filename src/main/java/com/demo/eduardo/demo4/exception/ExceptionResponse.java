package com.demo.eduardo.demo4.exception;

import java.util.Date;

public class ExceptionResponse {
	
	Date timestamp;
	String message;
	String details;
	
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	// No need for setters this time, just getters
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
