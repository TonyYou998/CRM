package Services;

import java.sql.SQLException;

import Modal.Project;
import dao.ProjectDao;

public class ProjectService {
	private ProjectDao projectDao;
	public ProjectService() {
		projectDao=new ProjectDao();
	}
	public void addNewProject(Project prj) {
		try {
			projectDao.addNewProject(prj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("unable to add new project");
		}
	}

}
