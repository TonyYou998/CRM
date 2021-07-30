package Services;

import java.sql.SQLException;

import dao.AuthDao;

public class AuthService {
	private AuthDao dao;
	public AuthService() {
		dao=new AuthDao();
		
	}
	public int login(String email,String password) {
		try {
			int result=dao.login( email, password);
			return result;
		}
		catch(SQLException e	) {
			e.printStackTrace();
			return 0;
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
