package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;



import Modal.Task;
import dbconnection.MySqlConnection;

public class TaskDao {
	
	private List<Task> tasks;
	private Task task;
	public List<Task> findAllTask(int id) throws SQLException {
		// TODO Auto-generated method stub
		String query="SELECT  t.id as id,t.name as  name,t.description as description,t.end_date as end,u.name as user_name,s.name as status_name from project p,status s,task t,user u where (t.project_id=p.id)and p.id=? and (t.status_id=s.id) and (u.id=t.user_id) order by t.id";
		Connection connection=MySqlConnection.getConnection();
		tasks=new LinkedList<Task>();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result= statement.executeQuery();
			while(result.next()) {
			
				task=new Task();
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				task.setDescription(result.getString("description"));
				task.setStatusName(result.getString("status_name"));
				task.setEnd(result.getString("end"));
//				task.setUserID(result.getInt("userID"));
				task.setUserName(result.getString("user_name"));
				tasks.add(task);
				
				
			}
			
			
			
			
		} catch (SQLException ex) {
			// TODO: handle exception
		}
		finally {
			connection.close();
		}
		return tasks;
	}
	public void deleteTaskByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		String query="DELETE FROM task WHERE task.id=?";
		Connection connection=MySqlConnection.getConnection();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			// TODO: handle exception
		}
		finally {
			connection.close();
		}
	}
	public void updateTaskStatus(int id) throws SQLException {
		// TODO Auto-generated method stub
		String query="update task\r\n"
				+ "set status_id=status_id+1\r\n"
				+ "where id=? and status_id<3";
		Connection connection=MySqlConnection.getConnection();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			connection.close();
		}
	}
	
}
