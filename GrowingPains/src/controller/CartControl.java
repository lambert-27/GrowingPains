package controller;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains controller package - BrowseControl - Controls the logic behind the different GUI operations when in the Cart section of the application
import java.sql.SQLException;
import java.util.List;

import javax.swing.JSpinner;

import exception.EmptyCartException;
import model.Cart;
import model.Customer;
import model.Order;
import model.OrderItem;
import view.GrowingPains;
import view.PaymentPanel;

/**
 * CartControl handles all of the logic behind the various GUI components associated with the Cart process of the system
 */
public class CartControl {
	
	
	/**
	 * Method which inserts a new order into the Orders database
	 * @param customer The currently logged in Customer
	 * @param cart The Customers cart
	 * @param cartItems The list of items within the customers cart
	 * @throws SQLException Exception thrown when an error with querying the databse occurs
	 */
	public void submitOrder(Customer customer, Cart cart, List<OrderItem> cartItems) throws SQLException {
		//Try block, checks if the cartItems list is empty, if it is, throw EmptyCartException
		try {
		if(!cartItems.isEmpty()) {
	//			Create a new order object for insertion
			Order order = new Order(customer.getCustomerID(), customer.getAddress(), cart.getTotalPrice());
			GrowingPains.getMainContent().add(new PaymentPanel(order, cart), "Payment");
			GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Payment");
		}else
		{
			throw new EmptyCartException("Cannot proceed to checkout with an empty cart");
		}
		}catch(EmptyCartException e1) {
			//Write the error to log
			GrowingPains.errorWriter.logError("Empty Cart Error: ", e1.getMessage());
		}
	}
	
	/**
	* Updates the cart values based on the spinner value for each cart item
	 * @param cart The current Cart object
	 * @param cartItems List of items within the cart
	 * @param spinners The list of qty spinners associated with each product in the cart
	 * @return The newly updated cart
	 */
	public List<OrderItem> updateCartValues(Cart cart, List<OrderItem> cartItems, List<JSpinner> spinners) {
	    try {
			// Check if cart is empty
	    if (!(cartItems == null || cartItems.isEmpty())) {
			double total = 0;
			int index = 0;
			//For each loop ot iterate through spinners
			for(JSpinner spin: spinners) {
				//Get the spinner @ current index
				spin = spinners.get(index);
	
				//Get product @ current index
				OrderItem product = cartItems.get(index);
				
				//New qty of items 
				int newQty = (int) spin.getValue();
				
//				If the new quantity is zero, remove the product from the list, 
				if(newQty == 0) {
					cartItems.remove(index);
				}else {
				//Set the new qty of items
				cartItems.get(index).setQty(newQty);
				}
				//Calculate total price
				total += newQty * product.getPrice();
				//Increment index to proceed to next item in loop
				index++;
			}
			cart.setTotalPrice(total);
	    }
	    else
	    	throw new EmptyCartException("Cannot update an empty cart");
		}catch(EmptyCartException e1){
			//Write the error to log
			GrowingPains.errorWriter.logError("Empty Cart Error: ", e1.getMessage());
		}
		return cartItems;
	}
}
