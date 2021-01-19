package entities;

public class Account {
	
	private int userId;
	private int accNum;
	private double balance;

	//no-args constructor
	public Account() {
		super();
	}
	
	//full-args constructor
	public Account(int userId, int accNum, double balance) {
		super();
		this.userId=userId;
		this.accNum=accNum;
		this.balance=balance;
	}
	
	public Account(int userId) {
		super();
		this.userId=userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccNum() {
		return accNum;
	}

	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", accNum=" + accNum + ", balance=" + balance + "]";
	}
	
	
	

}
