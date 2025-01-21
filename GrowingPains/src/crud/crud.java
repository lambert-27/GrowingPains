package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class crud {
	private Connection connection;
	private Statement statement;
	
	public crud(Connection connection, Statement statement) {
		this.connection = connection;
		this.statement = statement;
	}
	
	public void insertCustomer(String fName, String lName, String email, String address, String password, int phone){
		
		try {
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
	}
}
