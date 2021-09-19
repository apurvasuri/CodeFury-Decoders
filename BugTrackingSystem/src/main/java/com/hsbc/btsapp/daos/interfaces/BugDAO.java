package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.Bug;
import com.hsbc.btsapp.beans.User;

public interface BugDAO {
	
	void addBug(Bug bug);
	void updateBug(Bug bug);
	Bug getBugById(int bugId);
	List<Bug> getAllBugs();
	List<Bug> getBugByDevId(int devId);
	void deleteBug(Bug bug);

}
