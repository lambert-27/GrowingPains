package controller;

import java.util.List;

import javax.swing.JSpinner;

import model.Cart;
import model.Customer;
import model.Order;
import model.OrderItem;
import view.GrowingPains;
import view.PaymentPanel;

public class CartControl {
	
	
	public void submitOrder(Customer customer, Cart cart, List<OrderItem> cartItems) {
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
	 * @throws EmptyCartException 
	 */
	public void updateCartValues(Cart cart, List<OrderItem> cartItems, List<JSpinner> spinners) {
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
			//Calculate total price
			total += newQty * product.getPrice();
			//Set the new qty of items
			cartItems.get(index).setQty(newQty);
			//Increment index
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
}
}
