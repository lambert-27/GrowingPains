package crud;
//GROWING PAINS - Mark Lambert - C00192497
//Customer Crud class - Purpose to hold all methods for relevant Create, Retrieve, Update, Delete tasks for Customers
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
/**
 * The CustomerCrud class provides methods for performing CRUD (Create, Retrieve, Update, Delete)
 * operations in the Customer Table
*/
public class CustomerCrud extends Crud{
	//Constructs an CustomerCrud object which initialises the database connection via its superclass
	public CustomerCrud() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 *  Inserts a Customer into the Customer table 
	 * @param Customer cust A customer Object containing all details 
	 * (fName, lName, email, address, passWord, phone)
	 * to be entered into the Customer table
	 */
		public boolean insertCustomer(Customer cust) throws SQLException{
			try {
				//PreparedStatement for inserting a customer
				PreparedStatement pstat = connection.prepareStatement("INSERT INTO Customer(fName, lName, email, address, password, phone) VALUES(?,?,?,?,?,?)");
				//Sets the values for Customer table
				pstat.setString(1,  cust.getfName());
				pstat.setString(2,  cust.getlName());
				pstat.setString(3,  cust.getEmail());
				pstat.setString(4,  cust.getAddress());
				pstat.setString(5, cust.getPassword());
				pstat.setString(6, cust.getPhone());
				pstat.executeUpdate();
				return true;
			}
			catch(SQLException sqlException) {
//				After studying notes, realised I can print custom message here
				System.err.println("Error inserting into Customer table: " +  sqlException.getMessage());
				sqlException.printStackTrace();
			}
			return false;
		}

		/**
		 *  Retrieves a Customer item from the Customer table based on its customer ID
		 * @param customerID The ID of the customer to retrieve
		 * @return A Customer object
		 */
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
								resultSet.getString("phone"), 
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
		
		/**
		 *  Retrieves all Customers from the Customer table
		 * @return A List of Customer objects
		 */
		public List<Customer> getAllCustomers() throws SQLException {
			ResultSet resultSet = null;
			
//			ArrayList of products to store all selected products
			List<Customer> customers = new ArrayList<>();
			try {
				PreparedStatement pstat = connection.prepareStatement("SELECT customerID, fName, lName, email, address, password, phone FROM Customer");
				//Assign resultSet the value of the query
				resultSet = pstat.executeQuery();
				
//				Check if resultSet has a value
				while(resultSet.next()) {
					int id = resultSet.getInt("customerID");
					String fName = resultSet.getString("fName");
					String lName = resultSet.getString("lName");
					String email = resultSet.getString("email");
					String passWord = resultSet.getString("password");
					String adrs = resultSet.getString("address");
					String phone = resultSet.getString("phone");
					
					Customer customer = new Customer(id, fName, lName, email, passWord, phone, adrs);
					customers.add(customer);
					}
				}
				catch(SQLException sqlException) {
					System.err.println("Error retrieving all Customers from table : " + sqlException.getMessage());
					sqlException.printStackTrace();
				}
			
			return customers;
		}

		/**
		 *  Deletes a Customer from the Customer table based on its customer ID
		 * @param customerID The ID of the customer to retrieve
		 */
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
		
		/**
		 *  Updates a Customer from the Customer table based on its customer ID
		 * @param Customer cust The Customer object with which is being edited
		 */
		public void updateCustomer(Customer cust) throws SQLException {
			try {
				PreparedStatement pstat = connection.prepareStatement("UPDATE Customer SET fName=?, lName=?, email=?, address=?, password=?, phone=? WHERE customerID=?");
				
				pstat.setString(1,  cust.getfName());
				pstat.setString(2,  cust.getlName());
				pstat.setString(3,  cust.getEmail());
				pstat.setString(4,  cust.getAddress());
				pstat.setString(5, cust.getPassword());
				pstat.setString(6, cust.getPhone());
				pstat.setInt(7,  cust.getCustomerID());
				pstat.executeUpdate();
			}catch(SQLException sqlException) {
				System.err.println("Error retrieving customer from table: " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
		}
		
		/**
		 *  Verifies the credentials of a customer by checking the entered email and password
		 * against the existing customers in the Customer table
		 * @param email and passWord Retrieves a customer based on the email and password provided.
		 * @return true if a customer with the provided password and email exists, otherwise false.
		 */
		public Boolean login(String email, String passWord) throws SQLException{
			try
			{
				PreparedStatement pstat = connection.prepareStatement("SELECT * FROM Customer where email=? AND password=?");
				
				pstat.setString(1, email);
				pstat.setString(2, passWord);
				
				try (ResultSet resultSet = pstat.executeQuery()){
					return resultSet.next();
				}
			}catch(SQLException sqlException) {
				System.err.println("Error retrieving customer from table: " + sqlException.getMessage());
				sqlException.printStackTrace();
				return false;
			}

		}
		
		public Customer getCustomerByEmail(String email) throws SQLException {
			//Declare a new Customer
			Customer c = new Customer();
			//Declare a new ResultSet, default to null
			ResultSet resultSet = null;
			
			try {
				//Prepared statement for Querying the Customer table
				PreparedStatement pstat = connection.prepareStatement("SELECT customerID, fName, lName, email, address, password, phone FROM Customer WHERE email=?");
				pstat.setString(1,  email);
				//resultSet is assigned the result of the query
				resultSet = pstat.executeQuery();

					//Check if result has a value
					while(resultSet.next()) {
						//Creates a new Customer, inserting the values retrieved from the SELECT Query
						c = new Customer(
								resultSet.getInt("customerID"), 
								resultSet.getString("fName"), 
								resultSet.getString("lName"), 
								resultSet.getString("email"), 
								resultSet.getString("password"), 
								resultSet.getString("phone"), 
								resultSet.getString("address"));
				}
			}catch(SQLException sqlException) {
				System.err.println("Error retrieving customer from table: " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
			return c;
		}

}
