package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import Modal.Project;
import dbconnection.MySqlConnection;

public class ProjectDao {
	private List<Project> projects=new LinkedList<Project>();
	
	
	public Boolean addNewProject(Project project) throws SQLException {
		Connection connection=MySqlConnection.getConnection();
		Boolean isSuccess=true;
		String query="INSERT INTO project (name,description,start_date,end_date,owner) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			
			statement.setString(1, project.getProjectName());
			statement.setString(2, project.getProjectDescription());
			statement.setString(3, project.getStartDate());
			statement.setString(4, project.getEndDate());
			statement.setInt(5, project.getOwnerID());
			 statement.executeUpdate();
			
		}	catch(SQLException e) {
			System.out.println("unable to connect to databse");
			e.printStackTrace();
			isSuccess=false;
			
		}finally {
			connection.close();
		}
		
	
		return isSuccess;
		
	}
	public List<Project> findAllProject() throws SQLException{
		Connection connection=MySqlConnection.getConnection();
		String query="SELECT p.name as name, p.description as description, p.start_date as start,p.end_date as end,p.owner as owner  FROM project p";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			ResultSet result=statement.executeQuery();
			while(result.next()) {
				Project prj=new Project();
				prj.setProjectName(result.getString("name"));
				prj.setProjectDescription(result.getString("description"));
				prj.setStartDate(result.getString("start"));
				prj.setEndDate(result.getString("end"));
				prj.setOwnerID(result.getInt("owner"));
				
				projects.add(prj);
			}
			
		}
		catch(SQLException ex) {
			System.out.println("unable to connect to database");
			ex.printStackTrace();
		}
		finally {
			connection.close();
		}
		return projects;
	}
}
