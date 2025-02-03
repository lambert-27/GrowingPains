package crud;
//GROWING PAINS - Mark Lambert - C00192497
//Crud class - Purpose to hold all methods for relevant Create, Retrieve, Update, Delete tasks

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public void insertCustomer(Customer cust) throws SQLException{
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
//			After studying notes, realised I can print custom message here
			System.err.println("Error inserting into Customer table: " +  sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}

//	RETRIEVES a Customer from Customer table
	public Customer getCustomer() throws SQLException {
		//Declare a new Customer
		Customer c = new Customer();
		//Declare a new ResultSet, default to null
		ResultSet resultSet = null;
		
		try {
			//Prepared statement for Querying the Customer table
			PreparedStatement pstat = connection.prepareStatement("SELECT customerID, fName, lName, email, address, password, phone FROM Customer WHERE customerID=1");
			//resultSet is assigned the result of the query
			resultSet = pstat.executeQuery();

			System.out.println("-----Customers Table in GrowingPains DB-----");
				//Check if result has a value
				while(resultSet.next()) {
					//Creates a new Customer, inserting the values retrieved from the SELECT Query
					c = new Customer(
							resultSet.getInt("customerID"), 
							resultSet.getString("fName"), 
							resultSet.getString("lName"), 
							resultSet.getString("email"), 
							resultSet.getString("password"), 
							resultSet.getInt("phone"), 
							resultSet.getString("address"));
					//TEMP Print of Customer 				
				System.out.println(c);
			}
		}catch(SQLException sqlException) {
			System.err.println("Error retrieving customer from table: " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
		return c;
	}
	
//	Gets all Customers items in Customer table
	public void getAllCustomers() throws SQLException {
		ResultSet resultSet = null;
		
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT customerID, fName, lName, email, address, password, phone FROM Customer");
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("-----\nAll Customers in Customer Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("customerID") + ", " + resultSet.getString("fName") + ", " + 
				resultSet.getString("lName") + ", " + resultSet.getString("email") + ", " +
				resultSet.getString("password") + ", " + resultSet.getInt("phone") + ", " + resultSet.getString("address"));
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving all Customers from table: " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
	}

//	INSERT a Product into Product table
	public void insertProduct(Item item) throws SQLException{
		try {
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO Product(productName, description, price, qty, category) VALUES(?,?,?,?,?)");
			pstat.setString(1,  item.getItemName());
			pstat.setString(2,  item.getDescription());
			pstat.setDouble(3, item.getPrice());
			pstat.setInt(4,  item.getQty());
			pstat.executeUpdate();
		}
		catch(SQLException sqlException) {
			System.err.println("Error inserting Product into table : " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}
	
//	Gets a Plant item in Product table
	public Plant getPlant() throws SQLException {
		//Instantiate a new empty Plant object
		Plant p = new Plant();
		
		ResultSet resultSet = null;
		
		try {
			//SELECT Query which has a WHERE condition that the type must be an Plant AND productID=?
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category FROM Product where category='Plant' AND productID=3");
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("-----\nPlant in Product Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				p = new Plant(resultSet.getInt("productID"), 
						resultSet.getString("productName"), 
						resultSet.getString("description"), 
						resultSet.getDouble("price"), 
						resultSet.getInt("qty"));
				System.out.println(p);
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving Product from table : " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
		return p;
	}
	
//	Gets all Accessory items in Product table
	public Accessory getAccessory() throws SQLException {
		//Instantiate a new empty Plant object
		Accessory a = new Accessory();
		
		ResultSet resultSet = null;

		try {
			//SELECT Query which has a WHERE condition that the type must be an Accessory AND productID=?
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category FROM Product WHERE category='Accessory' AND productID=4");
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("-----\nAccessory in Accessory Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				a = new Accessory(resultSet.getInt("productID"), 
						resultSet.getString("productName"), 
						resultSet.getString("description"), 
						resultSet.getDouble("price"), 
						resultSet.getInt("qty"));
				System.out.println(a);
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving Accessory from table : " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
		return a;
	}
	

	
//	Gets all Plant items in Product table
	public void getAllPlants() throws SQLException {
		ResultSet resultSet = null;
		
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category FROM Product where category='Plant'");
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("\n-----All Plants in Product Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("productID") + ", " + resultSet.getString("productName") + ", " + 
				resultSet.getString("description") + ", " + resultSet.getDouble("price") + ", " +
				resultSet.getInt("qty") + ", " + resultSet.getString("category"));
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving all Plants from table : " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
	}
	
//	Gets all Accessory items in Product table
	public void getAllAccessories() throws SQLException {
		ResultSet resultSet = null;
		
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category FROM Product where category='Accessory'");
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("\n-----All Accessories in Product Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("productID") + ", " + resultSet.getString("productName") + ", " + 
				resultSet.getString("description") + ", " + resultSet.getDouble("price") + ", " +
				resultSet.getInt("qty") + ", " + resultSet.getString("category"));
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving all Accessories from table : " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
	}
	
//	Gets all Plant items in Product table
	public void getAllProducts() throws SQLException {
		ResultSet resultSet = null;
		
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT productID, productName, description, price, qty, category FROM Product");
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("\n-----All Products in Product Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("productID") + ", " + resultSet.getString("productName") + ", " + 
				resultSet.getString("description") + ", " + resultSet.getDouble("price") + ", " +
				resultSet.getInt("qty") + ", " + resultSet.getString("category"));
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving all Products from table : " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
	}
	
	public void insertOrder() throws SQLException {
		
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
