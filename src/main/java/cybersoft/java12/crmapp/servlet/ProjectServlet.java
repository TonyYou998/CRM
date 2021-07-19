package cybersoft.java12.crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modal.Project;
import Services.ProjectService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name="projectServlet",urlPatterns = {
		UrlConst.PROJECT_DASHBOARD,
		UrlConst.PROJECT_ADD,
		UrlConst.PROJECT_DELETE,
		UrlConst.PROJECT_MANAGE,
		UrlConst.PROJECT_STAFF,
		UrlConst.PROJECT_STAFF_ADD,
		UrlConst.PROJECT_STAFF_REMOVE
		
})
public class ProjectServlet extends HttpServlet {
	private Project prj;
	private ProjectService prjService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch (req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD: 
			getDashboard(req,resp);
			
			break;
		case UrlConst.PROJECT_MANAGE:
			break;
		case UrlConst.PROJECT_ADD:
			getProjectAdd(req,resp);
			break;
		case UrlConst.PROJECT_UPDATE:
			break;
		case UrlConst.PROJECT_DELETE:
			break;
		case UrlConst.PROJECT_STAFF:
			break;
		case UrlConst.PROJECT_STAFF_ADD:
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			break;
			
		
		default:
//			throw new IllegalArgumentException("Unexpected value: " + key);
			break;
		}
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
		case UrlConst.PROJECT_STAFF:
			break;
		case UrlConst.PROJECT_STAFF_ADD:
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			break;
			
		
		default:
//			throw new IllegalArgumentException("Unexpected value: " + key);
			break;
		}
	}

	private void postAddProject(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		String projectName=req.getParameter("project_name");
		String description=req.getParameter("description");
		String startDate=req.getParameter("start_date");
		String endDate=req.getParameter("end_date");
		String owner=req.getParameter("owner");
		prj=new Project();
		prj.setProjectName(projectName);
		prj.setProjectDescription(description);
		prj.setStartDate(startDate);
		prj.setEndDate(endDate);
		prj.setEndDate(endDate);
		prj.setOwner(owner);
		prjService=new ProjectService();
		prjService.addNewProject(prj);
		
		
		
	}

	private void getDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD).forward(req, resp);
		
	}
	
}
