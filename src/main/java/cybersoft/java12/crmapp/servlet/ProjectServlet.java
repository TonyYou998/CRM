package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modal.Project;
import Modal.ProjectUser;
import Modal.Task;
import Services.ProjectService;
import Services.TaskService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name="projectServlet",urlPatterns = {
		UrlConst.PROJECT_DASHBOARD,
		UrlConst.PROJECT_ADD,
		UrlConst.PROJECT_DELETE,
		UrlConst.PROJECT_MANAGE,
		UrlConst.PROJECT_STAFF,
		UrlConst.PROJECT_STAFF_ADD,
		UrlConst.PROJECT_STAFF_REMOVE,
		UrlConst.PROJECT_INFO
		
})
public class ProjectServlet extends HttpServlet {
	private Project prj;
	private ProjectService prjService;
	private	TaskService taskService;
	private List<Task> tasks;
	private List<ProjectUser> prjUsers;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		prjService=new ProjectService();
		taskService=new TaskService();
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		switch (req.getServletPath()) {
		
		case UrlConst.PROJECT_DASHBOARD: 
			getDashboard(req,resp);
			
			break;
		case UrlConst.PROJECT_INFO:
			getProjectInfo(req, resp);
			break;
		
		case UrlConst.PROJECT_ADD:
			getProjectAdd(req,resp);
			break;
		
		case UrlConst.PROJECT_DELETE:
			
			
			getProjectDelete(req, resp);
			
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			getProjectStaffRemoveByID(req,resp);
			break;
		
			
		
		default:
//			throw new IllegalArgumentException("Unexpected value: " + key);
			break;
		}
	}

	private void getProjectStaffRemoveByID(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		int projectID=Integer.parseInt(req.getParameter("id"));
		int userID=Integer.parseInt(req.getParameter("userID"));
		prjService.removeStaffByID(projectID,userID);
		resp.sendRedirect(req.getContextPath()+UrlConst.PROJECT_INFO+"?id="+projectID);
	}
	private void getProjectInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(req.getParameter("id"));
		tasks=new LinkedList<Task>();		
		tasks= taskService.getAllTask(id);
		prjUsers=new LinkedList<ProjectUser>();
		prjUsers=prjService.findStaffByProjectID(id);
		if(tasks!=null && !tasks.isEmpty() ) {
			req.setAttribute("tasks", tasks);
			
		}
		if(prjUsers!=null && !prjUsers.isEmpty()) {
			req.setAttribute("prjUsers", prjUsers);
		}
		req.setAttribute("projectID", id);
			
		
		req.getRequestDispatcher(JspConst.PROJECT_INFO).forward(req, resp);	
		
	}
	private void getProjectDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(req.getParameter("id"));
//		prjService=new ProjectService();
		prjService.deleteProjectByID(id);
		resp.sendRedirect(req.getContextPath()+UrlConst.PROJECT_DASHBOARD);
		
		
	}

	private void getProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(JspConst.PROJECT_ADD).forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch (req.getServletPath()) {
	
		case UrlConst.PROJECT_DASHBOARD: 
			postUpdateProject(req,resp);
			
			break;
		case UrlConst.PROJECT_MANAGE:
		
			break;
		case UrlConst.PROJECT_ADD:
			postAddProject(req,resp);
			break;
		case UrlConst.PROJECT_UPDATE:
			break;
		case UrlConst.PROJECT_DELETE:
			break;
		case UrlConst.PROJECT_INFO:
			postAddTask(req,resp);
			break;
		
		case UrlConst.PROJECT_STAFF:
			
			break;
		case UrlConst.PROJECT_STAFF_ADD:
			postAddStaff(req,resp);
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			
			break;
			
		
		default:
//			throw new IllegalArgumentException("Unexpected value: " + key);
			break;
		}
	}

	private void postAddStaff(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		ProjectUser staff=new ProjectUser();
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
	      LocalDate localDate = LocalDate.now();
		staff.setUserID(Integer.parseInt(req.getParameter("staffID")));
		staff.setRoleDescription(req.getParameter("staffDescription"));
		staff.setProjectID(Integer.parseInt(req.getParameter("projectID")));
	

	     staff.setJoinDate(dtf.format(localDate));
	    prjService.addStaff(staff);
	    resp.sendRedirect(req.getContextPath()+UrlConst.PROJECT_INFO+"?id="+staff.getProjectID());
	}
	private void postAddTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
			String taskName=req.getParameter("taskName");
			String taskDescription=req.getParameter("taskDescription");
			String start=req.getParameter("taskStart");
			String end=req.getParameter("taskEnd");
			int projectID=Integer.parseInt(req.getParameter("taskProjectID"));
			int staffID=Integer.parseInt(req.getParameter("staffID"));
			Task task=new Task();
			task.setProjectID(projectID);
			task.setName(taskName);
			task.setDescription(taskDescription);
			task.setStart(start);
			task.setEnd(end);
			task.setUserID(staffID);
			prjService.addTask(task);
			resp.sendRedirect(req.getContextPath()+UrlConst.PROJECT_INFO+"?id="+projectID);
			
	}
	private void postUpdateProject(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		int projectID=Integer.parseInt(req.getParameter("ID"));
		String projectName=req.getParameter("name");
		String projectDescription=req.getParameter("description");
		String projectStart=req.getParameter("start");
		String projectEnd=req.getParameter("end");
		int projectOwnerID=Integer.parseInt(req.getParameter("ownerID"));
		prj=new Project();
		prj.setProjectID(projectID);
		prj.setProjectName(projectName);
		prj.setProjectDescription(projectDescription);
		prj.setStartDate(projectStart);
		prj.setEndDate(projectEnd);
		prj.setOwnerID(projectOwnerID);
		prjService.updateProject(prj);
	}
	private void postAddProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String projectName=req.getParameter("project_name");
		String description=req.getParameter("description");
		String startDate=req.getParameter("start_date");
		String endDate=req.getParameter("end_date");
		int owner=Integer.parseInt(String.valueOf(req.getSession().getAttribute("userID")));
		prj=new Project();
		
		prj.setProjectName(projectName);
		prj.setProjectDescription(description);
		prj.setStartDate(startDate);
		prj.setEndDate(endDate);
		prj.setEndDate(endDate);
		prj.setOwnerID(owner);
		
		
//		prjService=new ProjectService();
		if(!prjService.addNewProject(prj)) {
			req.getRequestDispatcher(JspConst.PROJECT_ADD).forward(req, resp);
			return;

		}
//			
		
		resp.sendRedirect(req.getContextPath()+UrlConst.PROJECT_DASHBOARD);
		
		
	}

	private void getDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		prjService=new ProjectService();
		int roleID= Integer.parseInt(String.valueOf(req.getSession().getAttribute("roleID")));
		List<Project> projects;
		int userID=Integer.parseInt(String.valueOf(req.getSession().getAttribute("userID")));
		if( roleID==1)
			projects= prjService.findAllProject();
		else if(roleID==2)
			projects=prjService.findProjectLeader(userID);
		else projects=prjService.findUserProjectByID(userID);
		if(projects !=null && !projects.isEmpty()) 
			req.setAttribute("projects", projects);
		
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD).forward(req, resp);
		
	}
	
}
