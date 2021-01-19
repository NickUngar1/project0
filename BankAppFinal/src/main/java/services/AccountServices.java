package services;

import java.util.List;

import entities.Account;

public interface AccountServices {
	
	//AccountDAO Methods
	public boolean addAccount(Account a);
	public Account getAccount(int id);
	public Account getAccount(String username);
	public List<Account> getAllAccounts(int id);
	
	public Account getSpecificAccount(int accNum);
	
	public boolean updateAccount(Account change);
	public boolean deleteAccount(int id);
	
	public boolean deposit(Account dep, double balance);
	public boolean withdraw(Account with, double balance);

}
