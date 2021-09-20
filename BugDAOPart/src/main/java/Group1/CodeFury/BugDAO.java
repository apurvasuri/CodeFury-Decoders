package Group1.CodeFury;

import java.util.List;

public interface BugDAO {
	public int addBug(Bugs b) throws BugAlreadyExitsException;
	public int updateBugBybugId(Bugs b);
	public int DeleteBugs(int UniqueId) throws BugNotPresentException;
	public List<Bugs> getAllBugswithAprojectId(int projectId);
	public int updateBugByProjectId(Bugs b);
}
