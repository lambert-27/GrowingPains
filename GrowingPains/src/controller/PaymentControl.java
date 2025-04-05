package controller;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains controller package - CartControl - Controls the logic behind the different GUI operations when in the Payment section of the application
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import crud.OrderCrud;
import crud.ProductCrud;
import exception.EmptyFieldException;
import exception.ValidationException;
import model.Cart;
import model.Order;
import model.OrderItem;

/**
 * PaymentControl handles all of the logic behind the Order creation process
 */
public class PaymentControl {
	private OrderCrud orderCrud;
	private ProductCrud productCrud;
	
	/**
	 * Constructs a new PaymentControl object, initialising OrderCrud and ProductCrud objects used to interact with the database
	 * @throws SQLException Exception thrown if an error occurs when connecting to the db
	 */
	public PaymentControl() throws SQLException{
		this.orderCrud = new OrderCrud();
		this.productCrud = new ProductCrud();
	}
	
	/**
	 * Checks all of the fields within the PaymentPanel for some specified form of validation
	 * @param fName JTextField representing the cardholders  First Name
	 * @param lName JTextField representing the cardholders Last Name
	 * @param cardNumber JTextField representing the card number 
	 * @param cvv JPasswordField representing thee CVV
	 * @param expiryMonth JComboBox representing the 12 months of the year 
	 * @param expiryYear JComboBox representing the next 6 years 
	 * @throws ValidationException Exception thrown if an error occurs with validating the data
	 */
	public void checkFields(JTextField fName, JTextField lName, JTextField cardNumber, JPasswordField cvv, JComboBox<String> expiryMonth, JComboBox<String> expiryYear) throws ValidationException {
		checkInfo(fName, "First Name");
		checkInfo(lName, "Last Name");
		checkInfo(cardNumber, "Card Number");
		checkInfo(cvv, "CVV");
		

		//Check if the CVV is exactly 3 digits long, we convert the character array from .getPassword to a new String
		if(!((new String(cvv.getPassword()).matches("[\\d]{3}")))) {
			cvv.setBorder(BorderFactory.createLineBorder(Color.RED));
			throw new ValidationException("CVV must be exactly 3 digits");
		}
		
		//Check if the card number is exactly 16 digits long
		if(!(cardNumber.getText().matches("[\\d]{16}"))) {
			cardNumber.setBorder(BorderFactory.createLineBorder(Color.RED));
			throw new ValidationException("Card Number must be exactly 16 digits long");
		}
		
		//Check if the user selected an expiry date by checking if the default value is still selected
		if((expiryMonth.getSelectedItem() == "--" || expiryYear.getSelectedItem() == "--")) {
			throw new ValidationException("Must select expiry date");
		}
		
	}
	
	/**
	 * Makes an order and inserts the details of the order into the DB. Also makes a payment object for future expansion
	 * @param cart The customers cart
	 * @param order The customers order
	 * @throws ValidationException Exception for if the user enters invalid data
	 */
	public void makeOrder(Cart cart, Order order) throws ValidationException {
		try {
			//Get an updated list of the cart
			List<OrderItem> cartItems = cart.getCart();
//			Iterate through the cart
			for(OrderItem product : cartItems) {
//				For each item in the cart, execute the update query to update the product quantity
				productCrud.updateQty(product, product.getNewQty());
			}
//			Finally, insert the order
			orderCrud.insertOrder(order);
			cart.clearCart();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}				
	}
	
	/**
	 * Valid a text field method, encapsulates common code
	 * 
	 * @param fieldName for displaying the correct field name in the error message
	 * @param txt The JTextField object
	 * @throws EmptyFieldException occurs when an input field that is required is left empty
	 */
	public void checkInfo(JTextField txt, String fieldName) throws EmptyFieldException {
//		Basic validation to check if the user has atleast input some info in each textbox
		if(txt.getText().isEmpty()) {
			txt.setBorder(BorderFactory.createLineBorder(Color.RED));
			//Throw the new EmptyFieldException with the fieldName for output readability
			throw new EmptyFieldException(fieldName);
		}
		else
//			Set it back to black if it's valid
			txt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
