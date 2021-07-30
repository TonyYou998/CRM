package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Dto.UserCreateDto;
import Modal.Role;
import Modal.User;
import dbconnection.MySqlConnection;

public class UserDao {
	public List<User> findAll() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email, "
				+ "u.phone as phone, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "FROM user u, role r WHERE u.role_id = r.id order by u.id";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				
				int roleId = resultSet.getInt("role_id");
				Role role = getRoleFromList(roles, roleId);
				
				if(role == null) {
					role = new Role();
					role.setId(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));
					
					roles.add(role);
				}
				
				user.setRole(role);
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return users;
	}

	private Role getRoleFromList(List<Role> roles, int roleId) {
		for(Role role : roles)
			if(role.getId() == roleId)
				return role;
		return null;
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Connection connection = MySqlConnection.getConnection();
		String query = "DELETE FROM user WHERE id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
		}
		catch(SQLException ex) {
			
			
		}
	}

	public Boolean findUserById(int id) {
		// TODO Auto-generated method stub
		Boolean isExist=false;
		Connection connection=MySqlConnection.getConnection();
		String query="SELECT name FROM user u WHERE u.id=? ";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result= statement.executeQuery();
			if(result.next()) {
				isExist=true;
			}
		}
		catch(SQLException ex) {
			System.out.println("user not exist");
			
		}
		
		
		return isExist;
	}

	public String findUserByID(int id) throws SQLException {
		 String name="";
		// TODO Auto-generated method stub
		String query="SELECT name as name from user where id=?";
		
		Connection connection=MySqlConnection.getConnection();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			 statement.setInt(1, id);
			 ResultSet result=statement.executeQuery();
			if(result.next())
				name=result.getString("name");
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			connection.close();
		}
		return name;
	}

	public User findAllUserInfoByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		String query="SELECT id as id, name as name,email as email,password as password,phone as phone,address as address, role_id as role_id from user where id=?";
		
		Connection connection=MySqlConnection.getConnection();
		User user=new User();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			 statement.setInt(1, id);
			 ResultSet result=statement.executeQuery();
			if(result.next()) {
				
				user.setId(result.getInt("id"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setAddress(result.getString("address"));
				user.setPhone(result.getString("phone"));
				int roleId = result.getInt("role_id");
				Role role = new Role();
				role.setId(roleId);
				switch(roleId) {
					case 1:
						role.setName("admin");
						break;
					case 2:
						role.setName("leader");
						break;
					case 3:
						role.setName("member");
						break;
				
				}
				user.setRole(role);
				
				
				
			
			}
				
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			connection.close();
		}
		return user;
	}

	public void editUserByID(User user) throws SQLException {
		// TODO Auto-generated method stub
		String query="update user set email=?,name=?,address=?,phone=?,role_id=? where id=?";
		
		Connection connection=MySqlConnection.getConnection();

		try {
			PreparedStatement statement=connection.prepareStatement(query);
			 statement.setString(1, user.getEmail());
			 statement.setString(2, user.getName());
			 statement.setString(3, user.getAddress());
			 statement.setString(4, user.getPhone());
			 statement.setInt(5, user.getRoleID());
			 statement.setInt(6, user.getId());
			 
			 


			 
			statement.executeUpdate();
			
				
				
				
			
			
				
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			connection.close();
		}
	}

	public void addUser(UserCreateDto userDto) throws SQLException {
		// TODO Auto-generated method stub
		String query="insert into user (email,password,name,address,phone,role_id) values(?,?,?,?,?,?)";
		
		Connection connection=MySqlConnection.getConnection();
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			 statement.setString(1, userDto.getEmail());
			 statement.setString(2, userDto.getPassword());
			 statement.setString(3, userDto.getName());
			 statement.setString(4, userDto.getAddress());
			 statement.setString(5, userDto.getPhone());
			 statement.setInt(6, userDto.getRoleId());
			 
			 statement.executeUpdate();
			
		}
		catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
		finally {
			connection.close();
		}
	}

	
}
