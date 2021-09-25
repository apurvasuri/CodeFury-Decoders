package com.hsbc.btsapp.exceptions;

public class ProjectDoesNotExistException extends Exception {

	private static final long serialVersionUID = 7173531993167803647L;

	public ProjectDoesNotExistException(String message) {
		super(message);
	}

	public String toString() {
		return "Project with this id does not exist";
	}

}
