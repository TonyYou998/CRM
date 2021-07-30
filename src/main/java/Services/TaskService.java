package Services;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Modal.Task;
import dao.TaskDao;

public class TaskService {
	private TaskDao taskDao=new TaskDao();
	private List<Task> tasks;
	public List<Task> getAllTask(int id) {
		// TODO Auto-generated method stub
		 tasks=new LinkedList<Task>();
		try {
			tasks= taskDao.findAllTask(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tasks;
	}
	public void deleteTaskByID(int id) {
		// TODO Auto-generated method stub
		try {
			taskDao.deleteTaskByID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getUpdateStatus(int id) {
		// TODO Auto-generated method stub
		try {
			taskDao.updateTaskStatus(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
