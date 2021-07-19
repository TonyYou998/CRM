package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modal.Project;
import dbconnection.MySqlConnection;

public class ProjectDao {
	public void addNewProject(Project project) throws SQLException {
		Connection connection=MySqlConnection.getConnection();
		String query="INSERT INTO project (name,description,start_date,end_date,owner) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, project.getProjectName());
			statement.setString(2, project.getProjectDescription());
			statement.setString(3, project.getStartDate());
			statement.setString(4, project.getEndDate());
			statement.setString(5, project.getOwner());
			statement.executeUpdate();
			
		}	catch(SQLException e) {
			System.out.println("unable to connect to databse");
			e.printStackTrace();
			
		}finally {
			connection.close();
		}
		
	
		
		
	}
}
