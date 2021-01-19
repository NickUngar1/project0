package app;

import java.util.Scanner;

import entities.Account;
import entities.User;
import services.AccountServices;
import services.AccountServicesImpl;
import services.AdminServices;
import services.AdminServicesImpl;
import services.UserServices;
import services.UserServicesImpl;

public class Driver {
	
	private static UserServices us = new UserServicesImpl();
	private static AccountServices as = new AccountServicesImpl();
	private static AdminServices ads = new AdminServicesImpl();
	private static User loggedInUser = null;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		boolean isAdmin = false;
		boolean inProgram = true;
		
		while(inProgram) {
			boolean loggedIn=false;
			System.out.println("-------------------------------------------");
			System.out.println("---- Welcome to the Bank of AmeriNick! ----");
			System.out.println("-------------------------------------------");

			System.out.println("1 \t new user signup");
			System.out.println("2 \t sign in to existing user");
			System.out.println("3 \t sign is as admin");
			System.out.println("4 \t exit program");
			int choice1 = Integer.parseInt(scan.nextLine());
			
			switch(choice1) {
				case 1: {
					System.out.println("Create a username: ");
					String uName = scan.nextLine();
					System.out.println("Create a password: ");
					String pWord = scan.nextLine();
					User u = new User(uName, pWord);
					us.addUser(u);
					loggedInUser = us.login(uName, pWord);
					System.out.println("You have successfully logged in!");
					System.out.println("Welcome " + uName + "!");
					loggedIn=true;
					break;
				}
				case 2: {
					System.out.println("Enter username: ");
					String uName = scan.nextLine();
					System.out.println("Enter password: ");
					String pWord = scan.nextLine();
					loggedInUser = us.login(uName, pWord);
					System.out.println("You have successfully logged in!");
					System.out.println("Welcome " + uName + "!");
					loggedIn=true;
					break;
				}
				case 3: {
					System.out.println("Enter admin password: ");
					String input = scan.nextLine();
					if(input.equals("admin")) {
						isAdmin = true;
						//User admin = new User();
					}
						while(isAdmin) {
							
							System.out.println("You are signed in as an admin.");
							System.out.println("1 \t view all users");
							System.out.println("2 \t add users");
							System.out.println("3 \t delete all users and accounts");
							System.out.println("4 \t sign out");
							int adChoice = scan.nextInt();
							
							switch(adChoice) {
								case 1: {
									ads.adminGetAllUsers();
								}
								break;
								case 2: {
									ads.adminAddUser();
								}
								break;
								case 3: {
									System.out.println("Are you sure you would like to delete all users and account?");
									System.out.println("1 \t yes, delete all users and accounts");
									System.out.println("2 \t no");
									int sure = scan.nextInt();
									if (sure == 1) ads.adminDeleteUsers();
								}
								break;
								case 4: {
									System.out.println("Goodbye Admin.");
									isAdmin = false;
									break;
								}
							}
							
							
						}
					
				}
				case 4: {
					System.out.println("Thank you for using Bank of AmeriNick!");
					System.out.println("Goodbye!");
					inProgram=false;
				}
			} //switch
						
				while(loggedIn) {
					System.out.println("1 \t create new account");
					System.out.println("2 \t view your accounts");
					System.out.println("3 \t sign out");
					int choice2 = Integer.parseInt(scan.nextLine());
					
					
					switch(choice2) {
					case 1: {
						Account a = new Account(loggedInUser.getUserId());
						as.addAccount(a);
					}
					break;
					case 2: {
						System.out.println("Here are your current accounts"); 
						System.out.println("-----------------------------------------");
						as.getAllAccounts(loggedInUser.getUserId()); //this displays all user accounts
						System.out.println("-----------------------------------------");

						
						
						//option to deposit/withdraw from account
						System.out.println("Would you like to modify any of your accounts?");
						System.out.println("------");
						System.out.println("1  yes");
						System.out.println("2   no");
						System.out.println("------");

						int ch = Integer.parseInt(scan.nextLine());
						
						if(ch==1) {
							System.out.println("Enter the account number you would like to modify: ");
						
							int accNum = Integer.parseInt(scan.nextLine());
							System.out.println("Modify account #" + accNum);
							System.out.println("1 \t deposit");
							System.out.println("2 \t withdraw");
							System.out.println("3 \t delete account");
							int mod = Integer.parseInt(scan.nextLine());
							
							switch(mod) {
								case 1: {
									System.out.println("How much would you like to deposit: ");
									double dep = Double.parseDouble(scan.nextLine());
									as.deposit(as.getSpecificAccount(accNum), dep);
									
								}
								break;
								case 2: {
									System.out.println("How much would you like to withdraw: ");
									double with = Double.parseDouble(scan.nextLine());
									as.withdraw(as.getSpecificAccount(accNum), with);
								}
								break;
								case 3: {
									if(as.getSpecificAccount(accNum).getBalance()==0) {
										as.deleteAccount(accNum);
									}
									else {
										System.out.println("You can only delete accounts that are empty.");
									}
								}
								break;
							}
							
						}
						
					}
					break;
					case 3: {
						System.out.println("Goodbye!");
						loggedIn=false;
					}
					break;
					}
				}
			
			continue;
				
			
			}  //while(true)
		
		scan.close(); //this is needed but has to be put somewhere outside of the while(true)
		
		} //main
	
		
		
	

}
