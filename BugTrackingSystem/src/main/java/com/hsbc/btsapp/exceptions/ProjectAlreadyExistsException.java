package com.hsbc.btsapp.exceptions;

import java.util.Arrays;

public class ProjectAlreadyExistsException extends Exception{

	public String toString()
	{
		return "Project with this id already exist";
	}
	

}
