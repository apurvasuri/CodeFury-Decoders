

package com.hsbc.btsapp.factory;

import com.hsbc.btsapp.daos.implementations.BugDAOImpl;
import com.hsbc.btsapp.daos.implementations.ProjectDAOImpl;
import com.hsbc.btsapp.daos.implementations.TeamDAOImpl;
import com.hsbc.btsapp.daos.implementations.UserDAOImpl;
import com.hsbc.btsapp.daos.interfaces.BugDAO;
import com.hsbc.btsapp.daos.interfaces.ProjectDAO;
import com.hsbc.btsapp.daos.interfaces.TeamDAO;
import com.hsbc.btsapp.daos.interfaces.UserDAO;

public class DAOFactory {
	
	public static BugDAO getBugDAOImpl() {
		return new BugDAOImpl();
	}

	public static UserDAO getUserDAOImpl() {
		return new UserDAOImpl();
	}

	public static ProjectDAO getProjectDAOImpl() {
		return new ProjectDAOImpl();
	}

	public static TeamDAO getTeamDAOImpl() {
		return new TeamDAOImpl();
	}

}
