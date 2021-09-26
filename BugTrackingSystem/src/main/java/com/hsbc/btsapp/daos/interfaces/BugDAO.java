package com.hsbc.btsapp.daos.interfaces;

import java.text.ParseException;
import java.util.List;

import com.hsbc.btsapp.beans.Bug;
import com.hsbc.btsapp.exceptions.BugAlreadyExitsException;

public interface BugDAO {
	public int addBug(Bug b) throws BugAlreadyExitsException;

	public List<Bug> getAllBugsWithProjectId(String projectId) throws ParseException;

	public  Bug getBugWithBugId(String bugId);

	public int updateBugByBugId(Bug b);

	public int deleteBugByBugId(String bugId);

	public int deleteBugByProjectId(String projectId);
}
