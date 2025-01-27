package crud;
//GROWING PAINS - Mark Lambert - C00192497
//Crud class - Purpose to hold all methods for relevant Create, Retrieve, Update, Delete tasks

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;

public class Crud {
	private Connection connection;
	
	//Constructor
	public Crud(Connection connection) {
		//When we instantiate the CRUD class, so to do we instantiate a Connection
		this.connection = connection;
	}
	
	//Inserts a Customer object into SQL Database by values fName, lName, email, address, password, phone
	public void insertCustomer(Customer cust){
		try {
			//PreparedStatement for inserting a customer
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO Customer(fName, lName, email, address, password, phone) VALUES(?,?,?,?,?,?)");
			//Sets the values for Customer table
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

	public void getCustomer() {
		//Declare a new Customer
		Customer c ;
		//Declare a new ResultSet, default to null
		ResultSet resultSet = null;
		
		try {
			//Prepared statement for Querying the Customer table
			PreparedStatement pstat = connection.prepareStatement("SELECT customerID, fName, lName, email, address, password, phone FROM Customer where customerID=1");
			//resultSet is assigned the result of the query
			resultSet = pstat.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();

			System.out.println("-----Customers Table in GrowingPains DB-----");
				//Check if result has a value
				while(resultSet.next()) {
					//Call setters from Customer
					c = new Customer(
							resultSet.getInt("customerID"), resultSet.getString("fName"), 
							resultSet.getString("lName"), resultSet.getString("email"), 
							resultSet.getString("password"), resultSet.getInt("phone"), resultSet.getString("address"));
							
				System.out.println(c);
			}
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			}
				catch(Exception exception) {
					exception.printStackTrace();
				}
			}
		//return c;	
		}

	public void insertProduct(Item item) {
		try {
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO Product(productName, description, price, qty, category) VALUES(?,?,?,?,?)");
			pstat.setString(1,  item.getItemName());
			pstat.setString(2,  item.getDescription());
			pstat.setFloat(3, item.getPrice());
			pstat.setInt(4,  item.getQty());
			pstat.setString(5, item.getType());
			pstat.executeUpdate();
		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	public void insertOrder() {
		
	}
//	public void insertOrder(int customerID, int productID, Date date, Time time, String shippingAddress, float totalPrice) {
//		try {
//			PreparedStatement pstat = connection.prepareStatement("INSERT INTO Orders(customerID, productID, date, time, shippingAddress, totalPrice) VALUES(?,?,?,?,?,?)");
//			pstat.setInt(1,  customerID);
//			pstat.setInt(2,  productID);
//			pstat.setDate(3, date);
//			pstat.setTime(4,  time);
//			pstat.setString(5, shippingAddress);
//			pstat.setFloat(6, totalPrice);
//			pstat.executeUpdate();
//		}
//		catch(SQLException sqlException) {
//			sqlException.printStackTrace();
//		}
//	}
}
