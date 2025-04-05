package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains LoginPanel class - Contains structure for LoginPanel area of app


import java.awt.Color;

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

import controller.AccountControl;
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
	private JButton createAccount;
	private Customer customer;
	private boolean loggedIn;
	private JLabel errorLbl;
	private final AccountControl CONTROL = new AccountControl();
	/**
	 * Constructs a new LoginPanel, initialising the layout, title, buttons and input fields
	 * 
	 * @throws SQLException Error should a Customer not be found in the table 
	 */
	public LoginPanel() throws SQLException {
		loggedIn = false;
//		Using a gridbag layout for flexibility
		gbl = new GridBagLayout();
		setLayout(gbl);
//		GridBagConstraints lets us put some customisation onto our layout
		gbc = new GridBagConstraints();
//		Padding above and below each component in the GridBag container
		gbc.insets = new Insets(0, 0, 5, 5);
		
		
		//Initialise the error label
		errorLbl = new JLabel("Account not found!");
		errorLbl.setForeground(Color.RED); // Make it red for visibility
		errorLbl.setFont(GrowingPains.getArialFont());
		gbc.gridx = 0;
		gbc.gridy = 0;
		// Span both columns
		gbc.gridwidth = 2; 
		add(errorLbl, gbc);
		// Hide by default
		errorLbl.setVisible(false); 
		
		//Set grid width back to normal
		gbc.gridwidth = 1;
//		Email Label
		JLabel emailLbl = new JLabel("Email: ");
		//Set pos (Note, start at 0 for x on labels, then for the textfield to appear beside x =1
		gbc.gridx = 0;
		gbc.gridy=1;
		emailLbl.setFont(GrowingPains.getArialFont());
		add(emailLbl, gbc);
		
//		Email TextField
		email = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy=1;
		add(email, gbc);
		
		//Password Label
//		Note new line, meaning y pos has increased to 1
		gbc.gridx = 0;
		gbc.gridy=2;
		JLabel passlLbl = new JLabel("Password: ");
		passlLbl.setFont(GrowingPains.getArialFont());
		add(passlLbl, gbc);
		
		//Password Field
		pass = new JPasswordField(20);
		gbc.gridx = 1;
		gbc.gridy=2;
		add(pass, gbc);
		
		//Submit button
		submit = GrowingButton.createButton("Login");
		handleLogin();
		submit.setBorderPainted(false);
		submit.setFocusPainted(false);

		
		//Note skipped a line, y pos = 4;
		gbc.gridx = 0;
		gbc.gridy=4;	
		add(submit, gbc);
		
		//Create account button
		createAccount = GrowingButton.createButton("Create an Account");
		createAccount.setBorderPainted(false);
		createAccount.setFocusPainted(false);
		gbc.gridx = 1;
		gbc.gridy = 4;
		goToCreateAccount();
		add(createAccount, gbc);		
	}
	
	/**
	 * Method that holds logic for passing parameters to the crud.login method which, queries for the user w/ matching email/password combo
	 * 
	 * @return The logged in customer object
	 */
	public Customer login() {
		String custEmail = email.getText();
//		.getPassword() returns to us the array of characters that make up the password
//		therefore, we first want to cast it as a String
		String passWord = new String(pass.getPassword());

		try {
			customer = CONTROL.login(custEmail, passWord, customer);
			if(customer == null) {
				//Display error label if the user doesn't log in successfully
				errorLbl.setVisible(true);
			}
			else {
				loggedIn = true;
				return customer;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Return null for login fail
		return null;
	}
	
	/**
	 * Handles the event when users presses the login button
	 */
	public void handleLogin() {
		submit.addMouseListener(new MouseAdapter() {
			//When the user clicks the button to login, let them login
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});
		pass.addActionListener(new ActionListener(){
			//When the user presses ENTER, let them login
			public void actionPerformed(ActionEvent e) {
				handleLogin();
			}
		});
	}
	
	/**
	 * Returns the current customer logged in
	 * @throws SQLException Error should a Customer not be found in the table 
	 * 
	 * @return The Logged in Customer Object
	 */
	public Customer getLoggedInCustomer() throws SQLException {
		return this.customer;
	}
	
	/**
	 * Returns the state of the current user
	 * @return true/false if logged in
	 */
	public boolean checkLoggedIn() {
		return this.loggedIn;
	}
	
	/**
	 * Updates the customer once logged in
	 * @param customer The current active customer object
	 */
	public void updateCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * Creates a new CerateAccount panel and then switches the display to that screen
	 */
	public void goToCreateAccount() {
		createAccount.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					CreateAccountPanel createAccountPanel = new CreateAccountPanel();
					GrowingPains.getMainContent().add(createAccountPanel, "CreateAccount");
					GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "CreateAccount");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
}





