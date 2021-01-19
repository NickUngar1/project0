package services;

import java.util.List;

import daos.AccountDAO;
import daos.AccountDAOImpl;
import entities.Account;
import entities.User;

public class AccountServicesImpl implements AccountServices{
	
	AccountDAO ad = new AccountDAOImpl();
	
	public boolean addAccount(Account a) {
		return ad.addAccount(a);
	}
	
	public Account getAccount(int id) {
		return ad.getAccount(id);
	}

	public Account getAccount(String username) {
		return ad.getAccount(username);
	}

	public List<Account> getAllAccounts(int id) {
		return ad.getAllAccounts(id);
	}
	
	public Account getSpecificAccount(int accNum) {
		return ad.getSpecificAccount(accNum);
	}

	public boolean updateAccount(Account change) {
		return ad.updateAccount(change);
	}

	public boolean deleteAccount(int id) {
		return ad.deleteAccount(id);
	}
	
	public boolean deposit(Account dep, double balance) {
		double currBalance = dep.getBalance();
		double newBalance = currBalance + balance;
		dep.setBalance(newBalance);
		ad.updateAccount(dep);
		System.out.println("Deposit Successful!");
		System.out.println("New balance is " + newBalance);
		return true;
	}
	
	public boolean withdraw(Account with, double balance) {
		double currBalance = with.getBalance();
		double newBalance = currBalance - balance;
		with.setBalance(newBalance);
		ad.updateAccount(with);
		System.out.println("Withdraw Successful!");
		System.out.println("New balance is " + newBalance);
		return true;
	}

}
