package crud;
//GROWING PAINS - Mark Lambert - C00192497
//orderCrud class - Purpose to hold all methods for relevant Retrieve, Delete tasks for Order Items

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Order;
/**
 * The OrderCrud class provides methods for performing CRUD (Create, Retrieve, Update, Delete) 
 * operations in the Order Table
*/
public class OrderCrud extends Crud{
	/**
	 * Constructs an OrderCrud object used for CRUD operations on Orders
	 * @throws SQLException Error should a connection problem occur
	 */
	public OrderCrud() throws SQLException {
		super();
	}

	
	/**
	 *  Inserts a Order into the Orders table 
	 * @param order An Order Object containing all details to be entered into the Order table
	 * @throws SQLException Error for insertion of an Order into the Order table
	 */
	public void insertOrder(Order order) throws SQLException {
		try {
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO Orders(customerID, date, time, shippingAddress, totalPrice) VALUES(?,?,?,?,?)");
			pstat.setInt(1,  order.getCustomerID());
			pstat.setDate(2, order.getDate());
			pstat.setTime(3,  order.getTime());
			pstat.setString(4,  order.getAddress());
			pstat.setDouble(5, order.getPrice());
			pstat.executeUpdate();
		}
		catch(SQLException sqlException) {
			System.err.println("Error inserting Product into table : " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}

	/**
	 *  Retrieves an Order item from the Orders table based on its order ID
	 * @param orderID The ID of the order to retrieve
	 * @return An Order object
	 * @throws SQLException Error should an Order not be found in the table
	 */
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

				//Check if result has a value
				while(resultSet.next()) {
					//Creates a new Orders, inserting the values retrieved from the SELECT Query
					o = new Order(
							resultSet.getInt("orderID"),
							resultSet.getInt("customerID"),  
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
	
	/**
	 *  Retrieves all Orders from the Orders table via an INNER JOIN on the Customer table using the customer ID
     * @param customerID The logged in customer
	 * @return A List of Order objects
	 * @throws SQLException Error should an Order not be found in the table
	 */
	public List<Order> getAllOrders(int customerID) throws SQLException {
		ResultSet resultSet = null;
		List<Order> orders = new ArrayList<Order>();
		
		try {
			PreparedStatement pstat = connection.prepareStatement("SELECT orderID, Orders.customerID, date, time, shippingAddress, totalPrice FROM Orders INNER JOIN Customer ON "
					+ "Customer.customerID = Orders.customerID WHERE Customer.customerID = ?");
			//Set the customerID to look for
			pstat.setInt(1, customerID);
			//Assign resultSet the value of the query
			resultSet = pstat.executeQuery();
			
//			Iterate through the resultSet
			while(resultSet.next()) {
//				Assign values to a new temp Order and add it to the list
				int orderID = resultSet.getInt("orderID");
				Date date = resultSet.getDate("date");
				Time time = resultSet.getTime("time");
				String address = resultSet.getString("shippingAddress");
				Float price = resultSet.getFloat("totalPrice");
				
				Order o = new Order(orderID, customerID, date, time, address, price);
				orders.add(o);
			}
		}
			catch(SQLException sqlException) {
				System.err.println("Error retrieving all Orders from table: " + sqlException.getMessage());
				sqlException.printStackTrace();
			}
		
		return orders;
	}

	/**
	 *  Deletes an Order from the Orders table based on its order ID
	 * @param orderID The ID of the order to retrieve
	 * 
	 * @throws SQLException Error should an Order not be found in the table
	 */
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
					sqlException.printStackTrace();
				}
	}
	
	/**
	 *  Updates an Order from the Orders table based on its order ID
	 * @param order The Order Object with which is being edited
	 * @throws SQLException Error should an Order not be found in the table
	 */
	public void updateOrder(Order order) throws SQLException {
		try {
			PreparedStatement pstat = connection.prepareStatement("UPDATE Orders SET customerID=?, date=?, time=?, shippingAddress=?, totalPrice=? WHERE orderID=?");
			pstat.setInt(1,  order.getCustomerID());
			pstat.setDate(2, order.getDate());
			pstat.setTime(3,  order.getTime());
			pstat.setString(4,  order.getAddress());
			pstat.setDouble(5, order.getPrice());
			pstat.setInt(6,  order.getOrderID());
			pstat.executeUpdate();
		}catch(SQLException sqlException) {
			System.err.println("Error retrieving Order from table: " + sqlException.getMessage());
			sqlException.printStackTrace();
		}
	}

}
