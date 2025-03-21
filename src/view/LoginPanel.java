package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains LoginPanel class - Contains structure for LoginPanel area of app


import java.awt.CardLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import crud.CustomerCrud;
import model.Customer;

/** 
 * The LoginPanel class represents the Login Panel in the GrowingPains application
 * 
 * It holds the structure and logic for handling the login process with the use of a JPasswordField
 * for the password input
 */

public class LoginPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	//	Instance variables 
	private JTextField email;
	private JPasswordField pass;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private JButton submit;
	private JButton cancel;
	private CustomerCrud crud;
	private Customer customer;
	private String customerEmail;
	
	/**
	 * Constructs a new LoginPanel, initialising the layout, title, buttons and input fields
	 * 
	 * @param ARIAL the font used
	 * @param GREEN the colour used
	 * @param cart the cart containing the list of products to be displayed
	 * @param cl the Layout Manager used
	 * @param mainContent The main panel that holds the cards
	 */
	public LoginPanel(Font ARIAL, Color GREEN, CardLayout cl, JPanel mainContent) throws SQLException {
		crud = new CustomerCrud();
//		Using a gridbag layout for flexibility
		gbl = new GridBagLayout();
		setLayout(gbl);
//		GridBagConstraints lets us put some customisation onto our layout
		gbc = new GridBagConstraints();
//		Padding above and below each component in the GridBag container
		gbc.insets = new Insets(0, 0, 5, 5);
		
//		Email Label
		JLabel emailLbl = new JLabel("Email: ");
		//Set pos (Note, start at 0 for x on labels, then for the textfield to appear beside x =1
		gbc.gridx = 0;
		gbc.gridy=5;
		emailLbl.setFont(ARIAL);
		add(emailLbl, gbc);
		
//		Email TextField
		email = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy=5;
		add(email, gbc);
		
		//Password Label
//		Note new line, meaning y pos has increased to 6
		gbc.gridx = 0;
		gbc.gridy=6;
		JLabel passlLbl = new JLabel("Password: ");
		passlLbl.setFont(ARIAL);
		add(passlLbl, gbc);
		
		//Password Field
		pass = new JPasswordField(20);
		pass.addActionListener(new ActionListener(){
			//When the user presses ENTER, let them login
			public void actionPerformed(ActionEvent e) {
				if(handleLogin(cl, mainContent) != null) {
					try {
						customer = crud.getCustomerByEmail(customerEmail);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		gbc.gridx = 1;
		gbc.gridy=6;
		add(pass, gbc);
		
		//Submit button
		submit = createButton("Login", ARIAL, GREEN);

		submit.setBorderPainted(false);
		submit.setFocusPainted(false);
		submit.addMouseListener(new MouseAdapter() {
			//When the user clicks the button to login, let them login
			public void mouseClicked(MouseEvent e) {
				if(handleLogin(cl, mainContent) != null) {
					try {
						customer = crud.getCustomerByEmail(customerEmail);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		//Note skipped a line, y pos = 8;
		gbc.gridx = 0;
		gbc.gridy=8;	
		add(submit, gbc);
		
		//Cancel button
		cancel = createButton("Cancel", ARIAL, GREEN);
		cancel.setBorderPainted(false);
		cancel.setFocusPainted(false);
		gbc.gridx = 1;
		gbc.gridy = 8;
		add(cancel, gbc);
		
		
	}
	
	/**
	 * Method that holds logic for passing parameters to the crud.login method which, queries for the user w/ matching email/password combo
	 * 
	 * @param cl the Layout Manager used
	 * @param mainContent The main panel that holds the cards
	 */
	public Customer handleLogin(CardLayout cl, JPanel mainContent) {
		String custEmail = email.getText();
		customerEmail = custEmail;
//		.getPassword() returns to us the array of characters that make up the password
//		therefore, we first want to cast it as a String
		String passWord = new String(pass.getPassword());

		try {
			if(crud.login(custEmail, passWord)) {
//				Retrieve the customer from the database 
				customer = crud.getCustomerByEmail(custEmail);
				if(customer != null) {
				cl.show(mainContent, "Browse");
//				Call of static method showButtons, to show the sideBar when the customer is logged in
				GrowingPains.showButtons();
				return customer;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		Return null for login fail
		return null;
	}
	
	/**
	 * Returns the current customer logged in
	 */
	public Customer getLoggedInCustomer() throws SQLException {
		return this.customer;
	}
	
	/**
	 * Create a button method, encapsulate common code
	 * 
	 * @param name the text displayed on the button
	 * @param GREEN the colour used
	 * @param cart the cart containing the list of products to be displayed
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
}
