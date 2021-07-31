package Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modal.Project;
import Modal.ProjectUser;
import Modal.Task;
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
	public void deleteProjectByID(int id)  {
		// TODO Auto-generated method stub
		try {
			projectDao.deleteProjectByID(id);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	public void updateProject(Project prj) {
		// TODO Auto-generated method stub
		try {
			projectDao.updateProject(prj);
		}
		catch(Exception e) {
			
		}
	}
	public void addTask(Task task) {
		// TODO Auto-generated method stub
		try {
			projectDao.addTask(task);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public List<ProjectUser> findStaffByProjectID(int id) {
		// TODO Auto-generated method stub
		List<ProjectUser> prjUsers=projectDao.findStaffByID(id);
		return prjUsers;
	}
	public void addStaff(ProjectUser staff) {
		// TODO Auto-generated method stub
		try {
			projectDao.addStaff(staff);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void removeStaffByID(int id,int userID) {
		// TODO Auto-generated method stub
		try {
			projectDao.removeStaffByID(id,userID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Project> findUserProjectByID(int userID) {
		// TODO Auto-generated method stub
		List<Project> projects=new LinkedList<Project>() ;
		try {
			projects= projectDao.findProjectByID(userID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projects;
	}
	public List<Project> findProjectLeader(int userID) {
		// TODO Auto-generated method stub
		List<Project> projects=new LinkedList<Project>() ;
		try {
			projects= projectDao.findProjectLeader(userID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projects;
	}


	
	
}
