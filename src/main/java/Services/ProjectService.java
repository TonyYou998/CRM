package Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Modal.Project;
import dao.ProjectDao;

public class ProjectService {
	private ProjectDao projectDao;
	private UserServices userService=new UserServices();
	public ProjectService() {
		projectDao=new ProjectDao();
	}
	public Boolean addNewProject(Project prj) {
		Boolean isSuccess=false;
		if(!userService.checkUserExist(prj.getOwnerID()))
			return isSuccess;
		try {
			isSuccess= projectDao.addNewProject(prj);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("unable to add new project");
		}
		
		return isSuccess;
	}
	
	
	public List<Project> findAllProject(){
		List<Project> prjs=null;
		try {
			prjs=projectDao.findAllProject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return prjs;
	
	}

}
