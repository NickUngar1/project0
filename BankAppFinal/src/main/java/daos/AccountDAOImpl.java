package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Account;
import entities.User;
import util.JDBCConnection;

public class AccountDAOImpl implements AccountDAO{
	
	public static Connection conn = JDBCConnection.getConnection();

	public Account getAccount(int id) {
		
		try {
			
			String sql = "select * from accounts where u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Account a = new Account();
				a.setUserId(rs.getInt("A_ID"));
				a.setAccNum(rs.getInt("ACC_NUM"));
				a.setBalance(rs.getDouble("BALANCE"));
				
				return a;
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public List<Account> getAllAccounts(int id) {
		
		List<Account> accList = new ArrayList<Account>();
		
		try {
			
			String sql = "select * from accounts where u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account a = new Account();
				//a.setUserId(rs.getInt("U_ID"));	
				a.setUserId(id);
				a.setAccNum(rs.getInt("ACC_NUM"));				
				a.setBalance(rs.getDouble("BALANCE"));
				
				System.out.println(a);
				accList.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Account getSpecificAccount(int accNum) {
		
		List<Account> accList = new ArrayList<Account>();
		
		try {
			
			String sql = "select * from accounts where acc_num = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(accNum));
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account a = new Account();
				a.setUserId(rs.getInt("U_ID"));
				a.setAccNum(rs.getInt("ACC_NUM"));	
				a.setBalance(rs.getDouble("BALANCE"));
				
				accList.add(a);
				return a;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean addAccount(Account a) {
		
		try {
			
			String sql = "call add_account(?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, a.getUserId());
			
			cs.execute();
			System.out.println("Account Creation Successful");
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Account Creation Failed");
		}
		
		return false;
	}

	public boolean updateAccount(Account change) {
		
		try {
			
			String sql = "update accounts set balance = ? where acc_num = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
//			ps.setInt(1, change.getUserId());
//			ps.setInt(2, change.getAccNum());
			ps.setDouble(1, change.getBalance());
			ps.setInt(2, change.getAccNum());
			
			ps.executeQuery();
			System.out.println("Account successfully updated");
			return true;
			
		} catch (SQLException e) {
			System.out.println("Account update failed");
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean deleteAccount(int id) {
		
		try {
			
			String sql = "delete accounts where acc_num = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			
			ps.executeQuery();
			System.out.println("Account successfully deleted");
			return true;
			
		} catch (SQLException e) {
			System.out.println("Account deletion failed");
			e.printStackTrace();
		}
		
		return false;
	}

}
