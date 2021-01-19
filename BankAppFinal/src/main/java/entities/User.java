package entities;

public class User {
	
	private int userId;
	private String username;
	private String password;
	
	//no-args constructor
	public User() {
		super();
	}
	
	//full-args constructor
	public User(String username, String password, int userId) {
		super();
		this.username=username;
		this.password=password;
		this.userId=userId;
	}
	
	//id-less constructor
	public User(String username, String password) {
		super();
		this.username=username;
		this.password=password;
	}
	
	public User(int userId) {
		super();
		this.userId=userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	

}

