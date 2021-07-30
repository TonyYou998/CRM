package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.MySqlConnection;

public class AuthDao {
	
	public int login(String email, String password) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=MySqlConnection.getConnection();
		
		
		String query="SELECT  role_id AS result FROM user WHERE email=? AND password=? ";
		
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet=statement.executeQuery();
			if(!resultSet.next())
				return 0;
			return resultSet.getInt(1);			
		}
		catch(SQLException ex) {
			System.out.println(ex);
			ex.printStackTrace();
			return 0;
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
