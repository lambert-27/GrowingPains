package crud;
//GROWING PAINS - Mark Lambert - C00192497
//Customer Crud class - Purpose to hold all methods for relevant Create, Retrieve, Update, Delete tasks for Customers
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;

public class customerCrud extends Crud{
	
	public customerCrud(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
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
//				After studying notes, realised I can print custom message here
				System.err.println("Error inserting into Customer table: " +  sqlException.getMessage());
				sqlException.printStackTrace();
			}
		}

//		RETRIEVES a Customer from Customer table
		public Customer getCustomer(int customerID) throws SQLException {
			//Declare a new Customer
			Customer c = new Customer();
			//Declare a new ResultSet, default to null
			ResultSet resultSet = null;
			
			try {
				//Prepared statement for Querying the Customer table
				PreparedStatement pstat = connection.prepareStatement("SELECT customerID, fName, lName, email, address, password, phone FROM Customer WHERE customerID=?");
				pstat.setInt(1,  customerID);
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
		
//		Gets all Customers items in Customer table
		public void getAllCustomers() throws SQLException {
			ResultSet resultSet = null;
			
			try {
				PreparedStatement pstat = connection.prepareStatement("SELECT customerID, fName, lName, email, address, password, phone FROM Customer");
				//Assign resultSet the value of the query
				resultSet = pstat.executeQuery();
				
				System.out.println("-----\nAll Customers in Customer Table in GrowingPains DB-----");
//				Check if resultSet has a value
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

//		Deletes a Customer via their ID from table
		public void deleteCustomer(int customerID) throws SQLException {

					try {
						//Prepared statement for Querying the Customer table
						PreparedStatement pstat = connection.prepareStatement("DELETE FROM Customer WHERE customerID=?");
						pstat.setInt(1, customerID);
						//rowsAffected is assigned the result of the query
						int rowsAffected = pstat.executeUpdate();

//						If a row was affected (greater than 0) then success!
						if(rowsAffected > 0){
							System.out.println("The Customer with customer ID: " + customerID + " was deleted");
						}
//						Otherwise, not found
						else {
							System.out.println("The Customer with customer ID: " + customerID + " was not found");
						}
					}catch(SQLException sqlException) {
						System.err.println("Error retrieving customer from table: " + sqlException.getMessage());
						sqlException.printStackTrace();
					}
		}
		
//		Update an existing Customer via their customer ID to a new set of values (held in a new customer object)
		public void updateCustomer(Customer cust, int customerID) throws SQLException {
			try {
				PreparedStatement pstat = connection.prepareStatement("UPDATE Customer SET fName=?, lName=?, email=?, address=?, password=?, phone=? WHERE customerID=?");
				
				pstat.setString(1,  cust.getfName());
				pstat.setString(2,  cust.getlName());
				pstat.setString(3,  cust.getEmail());
				pstat.setString(4,  cust.getAddress());
				pstat.setString(5, cust.getPassword());
				pstat.setInt(6, cust.getPhone());
				pstat.setInt(7,  customerID);
				pstat.executeUpdate();
			}catch(SQLException sqlException) {
				System.err.println("Error retrieving customer from table: " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
		}

}
