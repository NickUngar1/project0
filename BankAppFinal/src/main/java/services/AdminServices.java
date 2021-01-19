package services;

import entities.User;

public interface AdminServices {
	
	public void adminGetAllUsers();
	
	public boolean adminAddUser();
	
	public boolean adminDeleteUsers();

}
