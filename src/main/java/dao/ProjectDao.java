package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import Modal.Project;
import Modal.ProjectUser;
import Modal.Task;
import dbconnection.MySqlConnection;

public class ProjectDao {
	private List<Project> projects;
	private List<ProjectUser> prjUsers;
	
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
		String query="SELECT p.id as project_id,p.name as name, p.description as description, p.start_date as start,p.end_date as end,p.owner as ownerID,u.name as owner  FROM project p,user u WHERE u.id=p.owner";
		projects=new LinkedList<Project>();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			ResultSet result=statement.executeQuery();
			while(result.next()) {
				Project prj=new Project();
				prj.setProjectName(result.getString("name"));
				prj.setProjectDescription(result.getString("description"));
				prj.setStartDate(result.getString("start"));
				prj.setEndDate(result.getString("end"));
				prj.setOwnerID(result.getInt("ownerID"));
				prj.setOwnerName(result.getString("owner"));
				prj.setProjectID(result.getInt("project_id"));
				
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
	public void deleteProjectByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=MySqlConnection.getConnection();
		String query="DELETE FROM project WHERE id=? ";
		try{
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			
		}
		catch(SQLException ex) {
			System.out.println("unable to delete project");
		}
		finally {
			connection.close();
		}
	}
	public void updateProject(Project prj) throws SQLException {
		// TODO Auto-generated method stub
		String query="UPDATE project set name=?, description=?, start_date=?, end_date=?, owner=? where id=?";
		Connection connection=MySqlConnection.getConnection();
		try {
			
			PreparedStatement statement=connection.prepareStatement(query);
				
				statement.setString(1, prj.getProjectName());
				statement.setString(2, prj.getProjectDescription());
				statement.setString(3, prj.getStartDate());;
				statement.setString(4, prj.getEndDate());
				statement.setInt(5, prj.getOwnerID());
				statement.setInt(6, prj.getProjectID());
				
				int result= statement.executeUpdate();
				System.out.printf("result:%i",result);
			
				
		}
		catch(SQLException ex) {
			
		}
		finally {
			connection.close();
		}
	}
	public void addTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		String query="INSERT INTO task (name, description, start_date, end_date, project_id, user_id, status_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection connection=MySqlConnection.getConnection();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, task.getName());
			statement.setString(2, task.getDescription());
			statement.setString(3, task.getStart());
			statement.setString(4, task.getEnd());
			statement.setInt(5, task.getProjectID());
			statement.setInt(6, task.getUserID());
			statement.setInt(7, 1);
			int row= statement.executeUpdate();
			System.out.printf("row affect:",row);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			connection.close();
		}
	}
	public List<ProjectUser> findStaffByID(int id) {
		// TODO Auto-generated method stub
		String query="select distinct  pu.user_id as userID ,pu.project_id as projectID ,u.name as name ,u.email as email,pu.join_date as joinDate,r.name as role\r\n"
				+ "from project_user pu,role r,user u,project p\r\n"
				+ "where (pu.user_id=u.id) and (p.id=pu.project_id) and  pu.project_id=? and (r.id=u.role_id) ";
				
				Connection connection=MySqlConnection.getConnection();
				prjUsers=new LinkedList<ProjectUser>();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result= statement.executeQuery();
			while(result.next()) {
				ProjectUser user=new ProjectUser();

				user.setProjectID(result.getInt("projectID"));
				user.setUserID(result.getInt("userID"));
				user.setUserName(result.getString("name"));
				user.setEmail(result.getString("email"));
				user.setRole(result.getString("role"));
				user.setJoinDate(result.getString("joinDate"));
				
				prjUsers.add(user);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return prjUsers;
	}
	public void addStaff(ProjectUser staff) throws SQLException {
		// TODO Auto-generated method stub
		String query="insert into project_user (project_id,user_id,join_date,role_description) values(?,?,?,?)";
		
		Connection connection=MySqlConnection.getConnection();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, staff.getProjectID());
			statement.setInt(2, staff.getUserID());
			statement.setString(3, staff.getJoinDate());
			statement.setString(4, staff.getRoleDescription());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			connection.close();
		}
		
	}
	public void removeStaffByID(int id,int userID) throws SQLException {
		// TODO Auto-generated method stub
		String query="DELETE FROM project_user WHERE (project_id = ?) and (user_id=?)";
				
		
		Connection connection=MySqlConnection.getConnection();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.setInt(2, userID);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			connection.close();
		}
	}
	
}
