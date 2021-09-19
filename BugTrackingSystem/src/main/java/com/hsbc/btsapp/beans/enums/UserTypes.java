package com.hsbc.btsapp.beans.enums;

public enum UserTypes {

	DEV("Developer"),
	PM("Project Manager"),
	TESTER("Tester");

	private String userType;
	
	UserTypes(String userType) {
        this.userType = userType;
    }
    
    public String getUserType() {
        return this.userType;
    }
}
