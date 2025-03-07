package crud;
//GROWING PAINS - Mark Lambert - C00192497
//orderCrud class - Purpose to hold all methods for relevant Retrieve, Delete tasks for Order Items
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Order;

public class OrderCrud extends Crud{
	
	public OrderCrud() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	
//	Inserts a new Order into the Orders table
	public void insertOrder(Order order) throws SQLException {
		try {
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO Orders(customerID, productID, date, time, shippingAddress, totalPrice) VALUES(?,?,?,?,?,?)");
			pstat.setInt(1,  order.getCustomerID());
			pstat.setInt(2,  order.getProductID());
			pstat.setDate(3, order.getDate());
			pstat.setTime(4,  order.getTime());
			pstat.setString(5,  order.getAddress());
			pstat.setFloat(6, order.getPrice());
			pstat.executeUpdate();
		}
		catch(SQLException sqlException) {
			System.err.println("Error inserting Product into table : " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}

//	RETRIEVES a Orders from Orders table
	public Order getOrder(int orderID) throws SQLException {
		//Declare a new Order
		Order o = new Order();
		//Declare a new ResultSet, default to null
		ResultSet resultSet = null;
		
		try {
			//Prepared statement for Querying the Orders table
			PreparedStatement pstat = connection.prepareStatement("SELECT orderID, customerID, productID, date, time, shippingAddress, totalPrice FROM Orders WHERE orderID=?");
			pstat.setInt(1, orderID);
			//resultSet is assigned the result of the query
			resultSet = pstat.executeQuery();

			System.out.println("-----Orders Table in GrowingPains DB-----");
				//Check if result has a value
				while(resultSet.next()) {
					//Creates a new Orders, inserting the values retrieved from the SELECT Query
					o = new Order(
							resultSet.getInt("orderID"),
							resultSet.getInt("customerID"), 
							resultSet.getInt("productID"), 
							resultSet.getDate("date"), 
							resultSet.getTime("time"), 
							resultSet.getString("shippingAddress"), 
							resultSet.getFloat("totalPrice"));
					//TEMP Print of Orders 				
				System.out.println(o);
			}
		}catch(SQLException sqlException) {
			System.err.println("Error retrieving order from table: " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
		return o;
	}
	
//	Gets all Orders items in Orders table
	public void getAllOrders() throws SQLException {
		ResultSet resultSet = null;
		
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT orderID, customerID, productID, date, time, shippingAddress, totalPrice FROM Orders");
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
			System.out.println("-----\nAll Orders in Order Table in GrowingPains DB-----");
//			Check if resultSet has a value
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("orderID") + ", " + resultSet.getInt("customerID") + ", " + 
						resultSet.getInt("productID") + ", " + resultSet.getDate("date") + ", " +
						resultSet.getTime("time") + ", " + resultSet.getString("shippingAddress") + ", " + resultSet.getFloat("totalPrice"));
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving all Orders from table: " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
	}

//	Deletes an Order via their ID from table
	public void deleteOrder(int orderID) throws SQLException {

				try {
					//Prepared statement for Querying the Customer table
					PreparedStatement pstat = connection.prepareStatement("DELETE FROM Orders WHERE orderID=?");
					pstat.setInt(1, orderID);
					//rowsAffected is assigned the result of the query
					int rowsAffected = pstat.executeUpdate();

//					If a row was affected (greater than 0) then success!
					if(rowsAffected > 0){
						System.out.println("The Order with order ID: " + orderID + " was deleted");
					}
//					Otherwise, not found
					else {
						System.out.println("The Order with order ID: " + orderID + " was not found");
					}
				}catch(SQLException sqlException) {
					System.err.println("Error retrieving order from table: " + sqlException.getMessage());
					sqlException.printStackTrace();
				}
	}
	
//	Update an existing Order via their order ID to a new set of values (held in a new customer object)
	public void updateOrder(Order order, int orderID) throws SQLException {
		try {
			PreparedStatement pstat = connection.prepareStatement("UPDATE Orders SET customerID=?, productID=?, date=?, time=?, shippingAddress=?, totalPrice=? WHERE orderID=?");
			pstat.setInt(1,  order.getCustomerID());
			pstat.setInt(2,  order.getProductID());
			pstat.setDate(3, order.getDate());
			pstat.setTime(4,  order.getTime());
			pstat.setString(5,  order.getAddress());
			pstat.setFloat(6, order.getPrice());
			pstat.setInt(7,  orderID);
			pstat.executeUpdate();
		}catch(SQLException sqlException) {
			System.err.println("Error retrieving customer from table: " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}

}
