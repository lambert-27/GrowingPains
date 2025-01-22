package crud;
//GROWING PAINS - Mark Lambert - C00192497
//Crud class - Purpose to hold all methods for relevant Create, Retrieve, Update, Delete tasks

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Date;
import model.Customer;
public class crud {
	private Connection connection;
	
	public crud(Connection connection) {
		this.connection = connection;
	}
	
	//Inserts a Customer object into SQL Database by values fName, lName, email, address, password, phone
	public void insertCustomer(Customer cust){
		try {
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO Customer(fName, lName, email, address, password, phone) VALUES(?,?,?,?,?,?)");
			pstat.setString(1,  cust.getfName());
			pstat.setString(2,  cust.getlName());
			pstat.setString(3,  cust.getEmail());
			pstat.setString(4,  cust.getAddress());
			pstat.setString(5, cust.getPassword());
			pstat.setInt(6, cust.getPhone());
			pstat.executeUpdate();
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	public void insertOrder(int customerID, int productID, Date date, Time time, String shippingAddress, float totalPrice) {
		try {
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO Orders(customerID, productID, date, time, shippingAddress, totalPrice) VALUES(?,?,?,?,?,?)");
			pstat.setInt(1,  customerID);
			pstat.setInt(2,  productID);
			pstat.setDate(3, date);
			pstat.setTime(4,  time);
			pstat.setString(5, shippingAddress);
			pstat.setFloat(6, totalPrice);
			pstat.executeUpdate();
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
