package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.EmptyFieldException;
import controller.ValidationException;
import crud.OrderCrud;
import crud.ProductCrud;
import model.Cart;
import model.Order;
import model.OrderItem;
import model.Payment;

public class PaymentPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField cardNumber;
	//Use of JComboBoxes for getting card expiry information
	private JComboBox<String> expiryMonth;
	private JComboBox<String> expiryYear;
	private JTextField cvv;
	private JTextField cardHolderFName;
	private JTextField cardHolderLName;
	private JButton pay;
	private JButton returnToMenu;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	
	public PaymentPanel(Font ARIAL, Color GREEN, CardLayout cl, JPanel mainContent, Order order, Cart cart) {
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5); //Set padding
		
		buildForm(ARIAL, GREEN, cl, mainContent, order, cart);			
	}
	
	/**
	 * Builds the form for making a payment
	 * 
	 * @param ARIAL the font used
	 * @param GREEN the colour used
	 * @param cardLayout the Layout Manager used
	 * @param mainContent The main panel that holds the cards
	 */
	public void buildForm(Font ARIAL, Color GREEN, CardLayout cl, JPanel mainContent, Order order, Cart cart) {
		//First Name
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("First Name: "), gbc);
		
//		Add 1 to the y so that the inputbox is beneath the label
		gbc.gridy = 1;
		cardHolderFName = createTextField();
		add(cardHolderFName, gbc);
		
		//Last Name
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(new JLabel("Last Name: "), gbc);
		
		gbc.gridy = 1;
		cardHolderLName = createTextField();
		add(cardHolderLName, gbc);
		
		//Card Number (next line)
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(new JLabel("Card Number: "), gbc);
		
		gbc.gridy = 3;
		cardNumber = createTextField();
		add(cardNumber, gbc);
		
		//cvv (next line)
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(new JLabel("CVV: "), gbc);
		
		gbc.gridy = 3;
		cvv = createTextField();
		add(cvv, gbc);

		//Expiry Month
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(new JLabel("Expiry Month: "), gbc);
		
		gbc.gridy = 5;
		//String containing the numeric representation of the months of the year, note -- used as a default
		String months[] = {"--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		expiryMonth = new JComboBox<String>(months);
		add(expiryMonth, gbc);
		
		//Expiry Year
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(new JLabel("Expiry Year: "), gbc);
		
		gbc.gridy = 5;
		//String of abbreviated years, leading to 2031, as most credit cards at latest expire in 2031, note -- used as a default
		String years[] = {"--", "25", "26", "27", "28", "29", "30", "31"};
		expiryYear = new JComboBox<String>(years);
		add(expiryYear, gbc);
		
				
//		Send data to DB
		pay = createButton("Place Order", ARIAL, GREEN);
		pay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try {
					makeOrder(cart, order, mainContent, cl);
				} catch (ValidationException e1) {
					//Send the error to log file if user doesn't enter valid info
					GrowingPains.errorWriter.logError("Validation Error",  e1.getMessage());
				}
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 10;
		add(pay, gbc);

//		Return back to login screen
		returnToMenu = createButton("Return to Cart", ARIAL, GREEN);
		returnToMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(mainContent,  "Cart");
			}			
		});
		
		gbc.gridx = 1;
		gbc.gridy = 10;
		add(returnToMenu, gbc);
		
	}
	
	
	public void makeOrder(Cart cart, Order order, JPanel mainContent, CardLayout cl) throws ValidationException {
		try {
			//Demonstration of a Payment object being made and temp call of a pament method to prevent java displaying warning
			String expiryDate =  expiryMonth.getSelectedItem() + "/" + expiryYear.getSelectedItem();
			Payment payment = new Payment(cardNumber.getText(), expiryDate,  cvv.getText(), cart.getTotalPrice());
			payment.getTotalPrice();
			//order.setPrice(payment.getTotalPrice());
			OrderCrud crud = new OrderCrud();
			ProductCrud productCrud = new ProductCrud();
			validateForm();
			//Get an updated list of the cart
			List<OrderItem> cartItems = cart.getCart();
//			Iterate through the cart
			for(OrderItem product : cartItems) {
//				For each item in the cart, execute the update query to update the product quantity
				productCrud.updateQty(product, product.getNewQty());
			}
//			Finally, insert the order
			crud.insertOrder(order);
			cart.clearCart();
			cl.show(mainContent, "Browse");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}				
	}
	
	/**
	 * Create a button method, encapsulate common code
	 * 
	 * @param name the text displayed on the button
	 * @param GREEN the colour used
	 * @param ARIAL the font used	
	 * @return the JButton created
	 */
	public JButton createButton(String name, Font ARIAL, Color GREEN) {
		JButton btn = new JButton(name);
//		Set the imageIcon
		btn.setForeground(Color.WHITE);
		btn.setFont(ARIAL);
		btn.setBackground(GREEN);
		btn.setBorderPainted(false);
		
		return btn;
	}
	/**
	 * Create a text field method, encapsulates common code
	 *@return The JTextField object 
	 */
	public JTextField createTextField() {
		JTextField txt = new JTextField();
		txt.setPreferredSize(new Dimension(300, 30));
		return txt;
		
	}
	
	/**
	 * Iterates through each text field in the form to check for empty inputs as well as some basic validation for inputting
	 * card details
	 * 
	 * @throws ValidationException occurs when some input field does not meet required validation
	 * 
	 */
	public void validateForm() throws ValidationException {
		try {
			checkInfo(cardHolderFName, "First Name");
			checkInfo(cardHolderLName, "Last Name");
			checkInfo(cardNumber, "Card Number");
			checkInfo(cvv, "CVV");

			//Check if the CVV is exactly 3 digits long
			if(!(cvv.getText().matches("[\\d]{3}"))) {
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
			
			//Catch the EmptyFieldException and throw the ValidationException, with the EmptyFieldException's error message
		}catch(EmptyFieldException e) {
			throw new ValidationException("Form could not validate: " + e.getMessage());
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
