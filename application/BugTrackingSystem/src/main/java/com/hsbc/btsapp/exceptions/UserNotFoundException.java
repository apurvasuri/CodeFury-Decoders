package com.hsbc.btsapp.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 4059121121349726686L;

	public UserNotFoundException(String errMessage) {
		super(errMessage);
	}
}
