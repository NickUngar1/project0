package daos;

import java.util.List;

import entities.User;

//CRUD Operations on user table along with admin capabilities

public interface UserDAO {
	
	//read
	public User getUser(int id);
	public User getUser(String username);
	public List<User> getAllUsers();
	
	//create
	public boolean addUser(User u);
	
	//update
	public boolean updateUser(User change);
	
	//delete
	public boolean deleteUser(int id);
	
	
	//ADMIN PRIVILEGES
	public void adminGetAllUsers();
	public boolean adminAddUser();
	public boolean adminDeleteUsers();


}
