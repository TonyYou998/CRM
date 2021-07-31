package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import Dto.UserCreateDto;
import Dto.UserLoginDto;
import dbconnection.MySqlConnection;

public class AuthDao {
	
	public UserLoginDto login(String email, String password) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=MySqlConnection.getConnection();
		UserLoginDto dto=new UserLoginDto(email, password);
		
//		BCrypt.checkpw();
		String query="SELECT  role_id as result,password as password,id as id FROM user WHERE email=?";
		
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, email);
			
			ResultSet resultSet=statement.executeQuery();
			if(!resultSet.next())
				return dto ;
			else {
				dto.setPassword(resultSet.getString(2));
				
				if(BCrypt.checkpw(password, dto.getPassword()))
				{
					dto.setRoleID(resultSet.getInt(1));
					dto.setId(resultSet.getInt(3));
					return dto;
				}
//					return resultSet.getInt(1);		
			}
				

				return dto;
		}
		catch(SQLException ex) {
			System.out.println(ex);
			ex.printStackTrace();
			return dto;
		}
		finally {
			connection.close(); //phair close
			
		}
		
		
	}

	public int checkRoleByID(int id) throws SQLException {
		// TODO Auto-generated method stub
Connection connection=MySqlConnection.getConnection();
		
		
		String query="SELECT role_id AS result FROM user WHERE id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet=statement.executeQuery();
			
			return resultSet.getInt("result");
		}
		catch(SQLException ex) {
			System.out.println("unable to connect to database");
			ex.printStackTrace();
			
		}
		finally {
			connection.close(); //phair close
			
		}
		return -1;
	}

}
