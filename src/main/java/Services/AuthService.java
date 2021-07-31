package Services;

import java.sql.SQLException;

import Dto.UserLoginDto;
import dao.AuthDao;

public class AuthService {
	private AuthDao dao;
	public AuthService() {
		dao=new AuthDao();
		
	}
	public UserLoginDto login(String email,String password) {
	
			try {
				return dao.login( email, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		
			
	
		
	}
	public int checkRole(int id) {
		// TODO Auto-generated method stub
		int role_id=-1;
		try {
			role_id=dao.checkRoleByID(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return role_id;
	}
}
