package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.User;
import util.JDBCConnection;

public class UserDAOImpl implements UserDAO{
	
	public static Connection conn = JDBCConnection.getConnection();
	Scanner scan = new Scanner(System.in);

	public User getUser(int id) {
		
		try {
			
			String sql = "select * from users where u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("U_ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				
				return u;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public User getUser(String username) {
		
		try {
			
			String sql = "select * from users where u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("U_ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				
				return u;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		
		try {
			
			String sql = "select * from users";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("U_ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				
				users.add(u);
			}
			
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean addUser(User u) {
		
		try {
			
			String sql = "call add_user(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, u.getUsername());
			cs.setString(2, u.getPassword());
			
			cs.execute();
			System.out.println("User successfully added");
			return true;
			
		} catch (SQLException e) {
			System.out.println("New user add failed");
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean updateUser(User change) {
		
		try {
			
			String sql = "update users set u_id = ?, username = ?, password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(change.getUserId()));
			ps.setString(2, change.getUsername());
			ps.setString(3, change.getPassword());
			
			ps.executeQuery();
			System.out.println("User successfully updated");
			return true;
			
		} catch (SQLException e) {
			System.out.println("User update failed");
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean deleteUser(int id) {
		
		try {
			
			String sql = "delete users where u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			
			System.out.println("User deletion successful");
			return true;
			
		} catch (SQLException e) {
			System.out.println("User deletion failed");
			e.printStackTrace();
		}
		
		return false;
	}
	
	//--------------------------
	//---- ADMIN PRIVILEGES ---- 
	//--------------------------

	
	//adminGetAllUsers differs from getAllUsers because it actually prints out each user along the way
	//getAllUsers is simply used for locating users within the table
	public void adminGetAllUsers() {
		List<User> users = new ArrayList<User>();
		
		try {
			
			String sql = "select * from users";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("U_ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				
				users.add(u);
				System.out.println(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public boolean adminAddUser() {
		boolean run = true;
		while (run == true) {
			try {
				System.out.println("Enter username");
				String username = scan.nextLine();
				System.out.println("Enter password");
				String password = scan.nextLine();
				
				String sql = "call add_user(?,?)";
				CallableStatement cs = conn.prepareCall(sql);
				cs.setString(1, username);
				cs.setString(2, password);
				
				cs.execute();
				System.out.println("Admin successfully added user");
				
				//admin has ability to add as many users as they wish
				System.out.println("1 \t add another user");
				System.out.println("2 \t stop");
				int choice = Integer.parseInt(scan.nextLine());
				if(choice==2) {
					run = false;
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println("Admin add failed");
				e.printStackTrace();
			}
		}
			
			return false;
	}
	
	public boolean adminDeleteUsers() {
		try {
			
			String sql = "delete from users";
			String sql2 = "delete from accounts";
			CallableStatement cs = conn.prepareCall(sql);
			CallableStatement cs2 = conn.prepareCall(sql2);
			
			//account must be deleted before user because of FK constraint set on userId in account table
			cs2.execute();
			cs.execute();

			System.out.println("All users and accounts have been deleted from system");
			return true;
			
		} catch (SQLException e) {
			System.out.println("Account and user deletion failed");
			e.printStackTrace();
		}
		
		return false;
	}
	

}
