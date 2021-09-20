package com.hsbc.btsapp.daos.interfaces;

import java.util.List;

import com.hsbc.btsapp.beans.Bug;
import com.hsbc.btsapp.exceptions.BugAlreadyExitsException;

public interface BugDAO {
	public int addBug(Bug b) throws BugAlreadyExitsException;
	public int updateBugByBugId(Bug b);
	public List<Bug> getAllBugswithAprojectId(int projectId);
	public int updateBugByProjectId(Bug b);
}
