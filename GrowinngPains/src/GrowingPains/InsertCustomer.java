package GrowingPains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertCustomer {
	
	public static void main(String[] args) {
		//database URL
		final String DATABSE_URL = "jdbc:mysql://localhost/plantsystem";
		
		Connection connection = null;
		Statement statement = null;
		String f_name = "Mark";
		String l_name = "Lambert";
		//String password = "yo123";
		String email = "NO";
		String address = "YES";
		
		try {
			
			//Establish connection to database
			connection = DriverManager.getConnection(DATABSE_URL, "root", "");
			//Create Statement for inserting into table
			statement = connection.createStatement();
			//Insert Data into database
			statement.executeUpdate("INSERT INTO customer(f_name, l_name, email, address)" + " VALUES" + "(" + f_name + ", " +  l_name + ", " + email + ", " + address + "')");
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO customer(f_name, l_name, email, address) VALUES(?,?,?,?)");
			pstat.setString(1, f_name);
			pstat.setString(2,  l_name);
			//pstat.setString(3,  password);;
			pstat.setString(3,  email);
			pstat.setString(4, address);
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
