package com.hsbc.btsapp.beans.enums;

import java.util.HashMap;
import java.util.Map;

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
    
    private static final Map<String, Status> map = new HashMap<>();
	static {
		for (Status types : Status.values()) {
			map.put(types.getStatus(), types);
		}
	}

	public static Status getStatus(String status) {
		return map.get(status);
	}

}
