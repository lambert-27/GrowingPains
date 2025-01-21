package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertCustomer {
	
	public static void main(String[] args) {
		final String DATABSE_URL = "jdbc:mysql://localhost:3306/growingpains";
		final String USER_NAME = "user";
		final String PASS_WORD = "Growing_up27";
		Connection connection = null;
		Statement statement = null;
		String fName = "John";
		String lName = "nhoJ";
		String password = "yo123";
		String email = "NO";
		String address = "YES";
		int phone = 12393;
		
		try {
			
			//Establish connection to database
			connection = DriverManager.getConnection(DATABSE_URL, USER_NAME, PASS_WORD);
			//Create Statement for inserting into table
			statement = connection.createStatement();
			//Insert Data into database
			statement.executeUpdate("INSERT INTO Customer(fName, lName, email, address, password, phone )" + 
			"VALUES" + "(" + fName + ", " + lName + ", " + email + ", " + address + ", " + password + ", " + phone + ")");
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO Customer(fName, lName, email, address, password, phone) VALUES(?,?,?,?,?,?)");
			pstat.setString(1,  fName);
			pstat.setString(2,  lName);
			pstat.setString(3,  email);
			pstat.setString(4,  address);
			pstat.setString(5, password);
			pstat.setInt(6, phone);
			pstat.executeUpdate();
		
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally{
			try {
				statement.close();
				connection.close();
			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
