package controller;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains controller package - OrderControl - Controls the logic behind the different GUI operations when in the orders section of the application
import java.sql.SQLException;
import java.util.List;

import crud.OrderCrud;
import model.Order;

/**
 * OrderControl handles the GUI operations for selecting and cancelling an order from the Customers existing orders
 */
public class OrderControl {
	private OrderCrud crud;
	
	/**
	 * Constructs a new OrderControl object and initialises an OrderCrud objecting 
	 * @throws SQLException Thrown if a problem occurs connecting to the db
	 */
	public OrderControl() throws SQLException {
		this.crud = new OrderCrud();
	}
	
	/**
	 * Gets all of the orders belonging to a customer by their ID
	 * @param customerID The customers ID
	 * @return The list of Orders created by the user
	 * @throws SQLException Exception thrown when a problem occurs when querying the db
	 */
	public List<Order> getOrders(int customerID) throws SQLException {
        List<Order> orders = crud.getAllOrders(customerID);
        return orders;
	}
	
	/**
	 * Deletes an Order from the Orders table
	 * @param orderID The order ID to delete
	 * @throws SQLException Exception thrown when an error with the query occurs
	 */
	public void deleteOrder(int orderID) throws SQLException {
		crud.deleteOrder(orderID);
	}
}