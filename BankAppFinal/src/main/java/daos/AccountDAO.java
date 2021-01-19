package daos;

import java.util.List;

import entities.Account;

public interface AccountDAO {
	
	
		public Account getAccount(int id);
		public Account getAccount(String username);
		public List<Account> getAllAccounts(int id);
		
		public Account getSpecificAccount(int accNum);
		
		
		public boolean addAccount(Account a);
		
	
		public boolean updateAccount(Account change);
		
	
		public boolean deleteAccount(int id);

}
