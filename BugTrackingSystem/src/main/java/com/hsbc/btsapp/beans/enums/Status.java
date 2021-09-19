package com.hsbc.btsapp.beans.enums;

public enum Status {
	
	INPROG("IN progress"),
	COMPLETED("Completed");
	private String status;
	
	Status(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return this.status;
    }

}
