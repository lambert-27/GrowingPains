package controller;

import java.sql.SQLException;
import java.util.List;

import crud.OrderCrud;
import model.Order;

public class OrderControl {
	private OrderCrud crud;
	
	public OrderControl() throws SQLException {
		this.crud = new OrderCrud();
	}
	
	public List<Order> getOrders(int customerID) throws SQLException {
        List<Order> orders = crud.getAllOrders(customerID);
        return orders;
	}
	
	public void deleteOrder(int orderID) throws SQLException {
		crud.deleteOrder(orderID);
	}
}