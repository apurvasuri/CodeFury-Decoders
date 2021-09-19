package com.hsbc.btsapp.exceptions;

public class ProjectDoesNotExistException extends Exception {
	public String toString()
	{
		return "Project with this id does not exist";
	}

}
