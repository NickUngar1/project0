package services;

import java.util.List;

import daos.UserDAO;
import daos.UserDAOImpl;
import entities.User;

public class UserServicesImpl implements UserServices{
	
	UserDAO ud = new UserDAOImpl();
	
	//runs dao create
	public boolean addUser(User a) {
		return ud.addUser(a);
	}

	//runs dao read
	public User getUser(int id) {
		return ud.getUser(id);
	}

	//runs dao read
	public User getUser(String username) {
		return ud.getUser(username);
	}

	//dao read
	public List<User> getAllUsers() {
		return ud.getAllUsers();
	}

	//runs dao update
	public boolean updateUser(User change) {
		return ud.updateUser(change);
	}

	//runs dao delete
	public boolean deleteUser(int id) {
		return ud.deleteUser(id);
	}
	
	//this method goes through ArrayList of usernames and deletes users that = passed in username and password
	public User login(String username, String password) {
		for(User u : ud.getAllUsers()) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}

}
