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

import controller.PasswordHasher;
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
	private JButton createAccount;
	private CustomerCrud crud;
	private Customer customer;
	private String customerEmail;
	private boolean loggedIn;
	/**
	 * Constructs a new LoginPanel, initialising the layout, title, buttons and input fields
	 * 
	 * @throws SQLException Error should a Customer not be found in the table 
	 */
	public LoginPanel() throws SQLException {
		loggedIn = false;
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
		gbc.gridy=0;
		emailLbl.setFont(GrowingPains.getArialFont());
		add(emailLbl, gbc);
		
//		Email TextField
		email = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy=0;
		add(email, gbc);
		
		//Password Label
//		Note new line, meaning y pos has increased to 1
		gbc.gridx = 0;
		gbc.gridy=1;
		JLabel passlLbl = new JLabel("Password: ");
		passlLbl.setFont(GrowingPains.getArialFont());
		add(passlLbl, gbc);
		
		//Password Field
		pass = new JPasswordField(20);
		pass.addActionListener(new ActionListener(){
			//When the user presses ENTER, let them login
			public void actionPerformed(ActionEvent e) {
				if(handleLogin() != null) {
					try {
						customer = crud.getCustomerByEmail(customerEmail);
						loggedIn = true;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		gbc.gridx = 1;
		gbc.gridy=1;
		add(pass, gbc);
		
		//Submit button
		submit = createButton("Login");

		submit.setBorderPainted(false);
		submit.setFocusPainted(false);
		submit.addMouseListener(new MouseAdapter() {
			//When the user clicks the button to login, let them login
			public void mouseClicked(MouseEvent e) {
				if(handleLogin() != null) {
					try {
						customer = crud.getCustomerByEmail(customerEmail);
						loggedIn = true;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		//Note skipped a line, y pos = 3;
		gbc.gridx = 0;
		gbc.gridy=3;	
		add(submit, gbc);
		
		//Cancel button
		createAccount = createButton("Create an Account");
		
		createAccount.setBorderPainted(false);
		createAccount.setFocusPainted(false);
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
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(createAccount, gbc);
		
		
	}
	
	/**
	 * Method that holds logic for passing parameters to the crud.login method which, queries for the user w/ matching email/password combo
	 * 
	 * @return The logged in customer object
	 */
	public Customer handleLogin() {
		String custEmail = email.getText();
		customerEmail = custEmail;
//		.getPassword() returns to us the array of characters that make up the password
//		therefore, we first want to cast it as a String
		String passWord = new String(pass.getPassword());

		try {
			
			String storedHashPass = crud.getHashedPass(custEmail);
			
			if(PasswordHasher.verifyPassword(passWord, storedHashPass)) {
				loggedIn = true;
//				Retrieve the customer from the database 
				customer = crud.getCustomerByEmail(custEmail);
				if(customer != null) {
					GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Welcome");
	//				Call of static method showButtons, to show the sideBar when the customer is logged in
					GrowingPains.showButtons();
					customer.setLoggedIn();
					return customer;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Return null for login fail
		return null;
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
	 * Create a button method, encapsulate common code
	 * @param name the text displayed on the button
	 * @return the JButton created
	 */
	public JButton createButton(String name) {
		JButton btn = new JButton(name);
//		Set the imageIcon
		btn.setForeground(Color.WHITE);
		btn.setFont(GrowingPains.getArialFont());
		btn.setBackground(GrowingPains.getColor());
		btn.setBorderPainted(false);
		
		return btn;
	}
	
	/**
	 * Updates the customer once logged in
	 * @param customer The current active customer object
	 */
	public void updateCustomer(Customer customer) {
		this.customer = customer;
	}
	
}





