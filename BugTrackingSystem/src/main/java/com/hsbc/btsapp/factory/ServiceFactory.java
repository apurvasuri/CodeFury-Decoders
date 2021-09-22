package com.hsbc.btsapp.factory;

import com.hsbc.btsapp.services.implementations.BugServiceImpl;
import com.hsbc.btsapp.services.implementations.ProjectServiceImpl;
import com.hsbc.btsapp.services.implementations.TeamServiceImpl;
import com.hsbc.btsapp.services.implementations.UserServiceImpl;
import com.hsbc.btsapp.services.interfaces.BugService;
import com.hsbc.btsapp.services.interfaces.ProjectService;
import com.hsbc.btsapp.services.interfaces.TeamService;
import com.hsbc.btsapp.services.interfaces.UserService;
public class ServiceFactory {
	
	public static BugService getBugServiceImpl() {
		return new BugServiceImpl();
	}

	public static UserService getUserServiceImpl() {
		return new UserServiceImpl();
	}

	public static ProjectService getProjectServiceImpl() {
		return new ProjectServiceImpl();
	}

	public static TeamService getTeamServiceImpl() {
		return new TeamServiceImpl();
	}

}
