package com.hsbc.btsapp.beans.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserTypes {

	DEV("Developer"), PM("Project Manager"), TESTER("Tester");

	private String userType;

	UserTypes(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return this.userType;
	}

	private static final Map<String, UserTypes> map = new HashMap<>();
	static {
		for (UserTypes types : UserTypes.values()) {
			map.put(types.getUserType(), types);
		}
	}

	public static UserTypes getUserType(String userType) {
		return map.get(userType);
	}

}
