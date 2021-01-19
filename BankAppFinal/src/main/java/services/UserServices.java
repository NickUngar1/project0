package services;

import java.util.List;

import entities.User;

public interface UserServices {
	
	//UserDAO Methods
	public boolean addUser(User a);
	public User getUser(int id);
	public User getUser(String username);
	public List<User> getAllUsers();
	public boolean updateUser(User change);
	public boolean deleteUser(int id);
	
	public User login(String username, String password);
	
	
	

}
