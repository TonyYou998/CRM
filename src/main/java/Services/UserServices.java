package Services;

import java.util.List;

import Modal.User;

import dao.UserDao;

public class UserServices {

	
	
	
	private UserDao dao;
	public  UserServices() {
		dao=new UserDao();
	}
	public Boolean checkUserExist(int id) {
		return dao.findUserById(id);
		
		
	}
	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> users=null;
		try {
			users=dao.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
	
		return users;
	}
	
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		try {
			dao.deleteById(id);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void editUser(User user) {
		// TODO Auto-generated method stub
		try {
			dao.editUserByID(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public User findUserByID(int id) {
		// TODO Auto-generated method stub
		User user=new User();
		try {
			user= dao.findAllUserInfoByID(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}
	
}
