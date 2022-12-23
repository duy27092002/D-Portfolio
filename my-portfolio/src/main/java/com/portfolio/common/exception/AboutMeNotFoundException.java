package com.portfolio.common.exception;

public class AboutMeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AboutMeNotFoundException(String message) {
		super(message);
	}

}
