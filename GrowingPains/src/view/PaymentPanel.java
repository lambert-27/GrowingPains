package view;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controller.PaymentControl;
import exception.ValidationException;
import model.Cart;
import model.Order;
import model.Payment;

/**
 * The PaymentPanel extends JPanel, it holds the form details for making a payment
 * This includes JComboBoxes for the expiry date fields, JTexfields for the remaining info
 * This panel makes use of a GridBagLayout
 * 
 */
public class PaymentPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField cardNumber;
	//Use of JComboBoxes for getting card expiry information
	private JComboBox<String> expiryMonth;
	private JComboBox<String> expiryYear;
	private JPasswordField cvv;
	private JTextField cardHolderFName;
	private JTextField cardHolderLName;
	private JButton pay;
	private JButton returnToMenu;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	//String containing the numeric representation of the months of the year, note -- used as a default
	private final String MONTHS[] = {"--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	//String of abbreviated years, leading to 2031, as most credit cards at latest expire in 2031, note -- used as a default
	private final String YEARS[] = {"--", "25", "26", "27", "28", "29", "30", "31"};
	private final PaymentControl CONTROL = new PaymentControl();
	/**
	 * Constructor for the PaymentPanel, which builds the form for displaying
	 * @param order The order the customer makes
	 * @param cart The customers cart
	 * @throws SQLException thrown if an error occurs when querying the database
	 */
	public PaymentPanel(Order order, Cart cart) throws SQLException {
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5); //Set padding
		
		buildForm(order, cart);			
	}
	
	/**
	 * Builds the form for making a payment
	 * @param order The order object the customer is making
	 * @param cart The customers cart
	 */
	public void buildForm(Order order, Cart cart) {
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
		cvv = new JPasswordField();
		cvv.setPreferredSize(new Dimension(300, 30));
		add(cvv, gbc);

		//Expiry Month
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(new JLabel("Expiry Month: "), gbc);
		
		gbc.gridy = 5;

		expiryMonth = new JComboBox<String>(MONTHS);
		add(expiryMonth, gbc);
		
		//Expiry Year
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(new JLabel("Expiry Year: "), gbc);
		
		gbc.gridy = 5;

		expiryYear = new JComboBox<String>(YEARS);
		add(expiryYear, gbc);
		
				
//		Send data to DB
		pay = GrowingButton.createButton("Place Order");
		handlePayment(cart, order);
		gbc.gridx = 0;
		gbc.gridy = 10;
		add(pay, gbc);

//		Return back to cart
		returnToMenu = GrowingButton.createButton("Return to Cart");
		returnToCart();		
		gbc.gridx = 1;
		gbc.gridy = 10;
		add(returnToMenu, gbc);
		
	}
	
	/**
	 * Makes an order and inserts the details of the order into the DB. Also makes a payment object for future expansion
	 * @param cart The customers cart
	 * @param order The customers order
	 * @throws ValidationException Exception for if the user enters invalid data
	 */
	public void makeOrder(Cart cart, Order order) throws ValidationException {
		//Demonstration of a Payment object being made and temp call of a pament method to prevent java displaying warning
		String expiryDate =  expiryMonth.getSelectedItem() + "/" + expiryYear.getSelectedItem();
		Payment payment = new Payment(cardNumber.getText(), expiryDate,  cvv.getPassword().toString(), cart.getTotalPrice());
		payment.getTotalPrice();
		CONTROL.makeOrder(cart, order);
		GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Browse");			
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
	 * Handles the event when the user clicks the pay button
	 * @param cart The current users cart of items
	 * @param order The order object the user is about to pay for
	 */
	public void handlePayment(Cart cart, Order order) {
		pay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try {
					makeOrder(cart, order);
				} catch (ValidationException e1) {
					//Send the error to log file if user doesn't enter valid info
					GrowingPains.errorWriter.logError("Validation Error",  e1.getMessage());
				}
			}
		});
	}
	
	/**
	 * Returns the user back to the Cart screen onclick
	 */
	public void returnToCart() {
		returnToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Cart");
			}			
		});
	}
}
