package services;

import daos.UserDAO;
import daos.UserDAOImpl;
import entities.User;

public class AdminServicesImpl implements AdminServices{

	UserDAO ud = new UserDAOImpl();
	
	public void adminGetAllUsers() {
		ud.adminGetAllUsers();
	}

	public boolean adminAddUser() {
		User u = new User();
		ud.adminAddUser();
		return true;
	}

	public boolean adminDeleteUsers() {
		ud.adminDeleteUsers();
		return true;
	}
	
	

}
