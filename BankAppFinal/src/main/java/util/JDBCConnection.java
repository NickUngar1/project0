package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCConnection {
	
	/*
	 * We are going to maintain and observe a single connection object within this class
	 *  If no connection exists, we will create one
	 *  If a connection does exist, return it
	 * */
	
	//connection is a class that comes from JDBC
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {		
			if(conn==null) {
				//make new connection
				
				//Oracle added a 'hot fix' to ensure that ojdbc drivers would correctly load at the beginning of your application starting
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				
				//to establish a connection we need 3 credentials
				//url (endpoint), username, password
//				String endpoint = "ungar-db.chncvnsj7zkv.us-east-1.rds.amazonaws.com";
//				String url = "jdbc:oracle:thin:@" + endpoint + ":1521:ORCL"; 
//				String username = "nick";
//				String password = "password";
				Properties props = new Properties();
				FileInputStream input = new FileInputStream(JDBCConnection.class.getClassLoader().getResource("connection.properties").getFile());
				props.load(input);
				
				//test
				//String rds_url = System.getProperty("rds_url");
				
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				//establish our connection
				conn = DriverManager.getConnection(url, username, password);	
				return conn;
			}
			else {
				//return connection that already exists
				return conn;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * Below is for testing purposes
	 * not needed to use JDBC
	 * you do not need this in Project0
	 * */
	
//	public static void main(String[] args) {
//		
//		Connection conn1 = getConnection();
//		System.out.println(conn1);
//		
//	}
	
	//above will return 'oracle.jdbc.driver.T4CConnection@8f4ea7c' if connection has been made


}

