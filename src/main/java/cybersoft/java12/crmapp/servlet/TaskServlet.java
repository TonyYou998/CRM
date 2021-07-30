package cybersoft.java12.crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.TaskService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name="taskServlet",urlPatterns = {
		UrlConst.TASK_DELETE,
		UrlConst.TASK_UPDATE_STATUS
})
public class TaskServlet extends HttpServlet {
	private TaskService service;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		service=new TaskService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch (req.getServletPath()) {
		case UrlConst.TASK_DELETE: 
			getTaskDelete(req,resp);
			
			break;
		case UrlConst.TASK_UPDATE_STATUS:
			getTaskUpdateStatus(req,resp);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
		}
	}
	
	private void getTaskUpdateStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(req.getParameter("id"));
		service.getUpdateStatus(id);
		resp.sendRedirect(req.getContextPath()+UrlConst.PROJECT_DASHBOARD);
		
		
	}
	private void getTaskDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		int id =Integer.parseInt(req.getParameter("id"));
		service.deleteTaskByID(id);
		resp.sendRedirect(req.getContextPath()+UrlConst.PROJECT_DASHBOARD);
	}
	
}
