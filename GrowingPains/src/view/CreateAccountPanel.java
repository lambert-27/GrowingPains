package view;
//GROWING PAINS - Mark Lambert - C00192497
//CreateAccountPanel View class - Contains structure for account creation 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.AccountCreationException;
import controller.EmptyFieldException;
import controller.InvalidEmailException;
import controller.PasswordInconsistentException;
import controller.ValidationException;
import crud.CustomerCrud;
import model.Address;
import model.Customer;
/** 
 * The CreateAccountPanel class represents the Create Account Panel in the GrowingPains application
 * 
 * It holds the structure and logic for handling the account creation process with the use of a JPasswordField
 * for the password input. Before submission, some basic validation is done and on success a Customer object is
 * created and sent to the database
 */
public class CreateAccountPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField fName;
	private JTextField lName;
	private JTextField email;
	private JTextField adrs;
	private JTextField phone;
	private JPasswordField password;
	private JPasswordField confirmPass;
	private JButton submit;
	private JButton returnToLogin;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private CustomerCrud crud;
	
	/**
	 * Constructs a new CreateAccountPanel, initialising the layout,  buttons and input fields
	 * 
	 * 
	 * @throws SQLException Error for DB operations
	 */
	public CreateAccountPanel() throws SQLException {		
		crud = new CustomerCrud();
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5); //Set padding
		
		buildForm();			
	}
	
	/**
	 * Builds the form for account creation
	 * 
	 */
	public void buildForm() {
		//First Name
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("First Name: "), gbc);
		
//		Add 1 to the y so that the inputbox is beneath the label
		gbc.gridy = 1;
		fName = createTextField();
		add(fName, gbc);
		
		//Last Name
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(new JLabel("Last Name: "), gbc);
		
		gbc.gridy = 1;
		lName = createTextField();
		add(lName, gbc);
		
		//Email (next line)
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(new JLabel("Email: "), gbc);
		
		gbc.gridy = 3;
		email = createTextField();
		add(email, gbc);
		

		//Phone
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(new JLabel("Phone: "), gbc);
		
		gbc.gridy = 3;
		phone = createTextField();
		add(phone, gbc);
		
		//Address (next line)
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(new JLabel("Address: "), gbc);
		
		gbc.gridy = 5;
		adrs = createTextField();
		add(adrs, gbc);
		
		//Pass (next line)
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(new JLabel("Password: "), gbc);
		
		gbc.gridy = 8;
		password = new JPasswordField();
		password.setPreferredSize(new Dimension(300, 30));
		add(password, gbc);
		
		//Confirm Pass
		gbc.gridx = 1;
		gbc.gridy = 6;
		add(new JLabel("Confirm Password: "), gbc);
		
		gbc.gridy = 8;
		confirmPass = new JPasswordField();
		confirmPass.setPreferredSize(new Dimension(300, 30));
		add(confirmPass, gbc);
		
		
//		Send data to DB
		submit = createButton("Submit");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try {
					createAccount(); 
					//Display a success message when the user succesfully creates an account
					//CreateAccountPanel.this ensures the OptionPane appears centered over the window
					//Message Text is the main content of the alert
					//Success is the title
					//INFORMATION_MESSAGE indicates the message type, in this case, for information
					JOptionPane.showMessageDialog(CreateAccountPanel.this, "Account created!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				//Catch the Exceptions in order of the Exception hierarchy, from more specific to broad
				catch(PasswordInconsistentException e1) 
				{
					handleError(e1, "Password Error");
//					e1.printStackTrace();
				}
				catch (EmptyFieldException e2) 
				{
					handleError(e2,  "Empty Input Field");
//					e2.printStackTrace();
				}
				catch(AccountCreationException e3) 
				{
					handleError(e3,  "Account Creation Failure");
//					e3.printStackTrace();
				}
				catch (ValidationException e4) 
				{
					handleError(e4, "Validation Error");
//					e4.printStackTrace();
				}
				
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 10;
		add(submit, gbc);

//		Return back to login screen
		returnToLogin = createButton("Return to Login");
		returnToLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GrowingPains.getCardLayout().show(GrowingPains.getMainContent(),  "Login");
			}			
		});
		
		gbc.gridx = 1;
		gbc.gridy = 10;
		add(returnToLogin, gbc);
		
	}
	
	/**
	 * Create a button method, encapsulate common code
	 * 
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
	 * Create a text field method, encapsulates common code
	 *@return The JTextField object 
	 */
	public JTextField createTextField() {
		JTextField txt = new JTextField();
		txt.setPreferredSize(new Dimension(300, 30));
		return txt;
		
	}
	
	/**
	 * 	 * Method which handles the account creation process
	 * On success (if passwords are equal and not empty as well as all other form fields containing
	 * some info, then insert
	 * 
	 * @throws AccountCreationException For error with account creation
	 * @throws ValidationException note how we throw ValidationException, which means we can use our child exception classes 
	 * PasswordInconsistent and EmptyField, as ValidationException is their super class
	 * 
	 */
	public void createAccount() throws AccountCreationException, ValidationException {

		try {
			
			String fName = this.fName.getText();
			String lName = this.lName.getText();
			String email = this.email.getText();
			String adrs = this.adrs.getText();
	//		Note for JPasswordField we cast the char array returned by getPassword to a String
			String password = new String(this.password.getPassword());
			String confirmPass = new String(this.confirmPass.getPassword());
			String phone = this.phone.getText();
			Address customerAdrs = new Address(adrs);
			
			//Validate the inputs of the form
			validateForm();

			validatePasswords(password, confirmPass);
						
			//Create the customer for insertion
			Customer customer = new Customer(fName, lName, email, password, phone, customerAdrs);
			
			//Finally, if the crud operation fails, throw an AccountCreationException
			if(!(crud.insertCustomer(customer))) {
				throw new AccountCreationException("Account creation failed");
			}
			
			//Switch to the Login panel on success
			GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Login");
			//Catch an SQLException if all other Exception throws do not yield
		}catch (SQLException e) {
			throw new AccountCreationException("An error occured with your SQL Statement/Database: " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block for hashed password
			e.printStackTrace();
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
	
	/**
	 * Iterates through each text field in the form to check for empty inputs
	 * 
	 * @throws ValidationException occurs when some input field does not meet required validation
	 * 
	 */
	public void validateForm() throws ValidationException {
		try {
			checkInfo(fName, "First Name");
			checkInfo(lName, "Last Name");
			checkInfo(email, "Email");
			checkInfo(adrs, "Address");
			checkInfo(phone, "Phone");
						
			//Check if password fields are empty
			if(password.getPassword().length == 0) {
				//Concatenate the name of the field onto the string for the exception
				this.password.setBorder(BorderFactory.createLineBorder(Color.RED));
				throw new EmptyFieldException("Password Field");
			}
				
			//Check if the phone number field contains digits and spaces and plus (A very simple phone number validation using regex pattern)
			if(!(phone.getText().matches("[\\d +]*"))) {
				phone.setBorder(BorderFactory.createLineBorder(Color.RED));
				throw new ValidationException("Phone Number must only have digits or spaces");
			}
			
			//Check if the email at least has some text w/ an @ symbol
			//the . represents any character, followed by a * token to indicate at least once
			//then exactly one @ symbol, followed by more text
			if(!(email.getText().matches(".*@.*"))) {
				email.setBorder(BorderFactory.createLineBorder(Color.RED));
				throw new InvalidEmailException("Email must have an @");
			}
			
			//Catch the EmptyFieldException and throw the ValidationException, with the EmptyFieldException's error message
		}catch(EmptyFieldException e) {
			throw new ValidationException("Form could not validate: " + e.getMessage());
		}
				
	}
	
	/**
	 * Holds logic for password validation checks 
	 * @param password The customers new Password
	 * @param confirmPass The confirmation of their password
	 * @throws ValidationException Exception for problem with password validation
	 */
	public void validatePasswords(String password, String confirmPass) throws ValidationException{
		
		//If the passwords don't match, throw a PasswordInconsistentException
		if(!(password.equals(confirmPass)))
		{
			this.password.setBorder(BorderFactory.createLineBorder(Color.RED));
			this.confirmPass.setBorder(BorderFactory.createLineBorder(Color.RED));
			throw new PasswordInconsistentException("Passwords do not match");
		}
		//If the passwords are empty, throw a EmptyFieldException
		if(password.isEmpty() || confirmPass.isEmpty())
		{
			throw new EmptyFieldException("Either password fields ");
		}
	}
	
	/**
	 * Helper variable used to display error messages to the user via a JOptionPane and write the error to an error file
	 * @param errorType The type of error that occured
	 * @param e The Exception occured
	 */
	private void handleError(Exception e, String errorType) {
	    JOptionPane.showMessageDialog(CreateAccountPanel.this, e.getMessage(), errorType, JOptionPane.ERROR_MESSAGE);
	    GrowingPains.errorWriter.logError(errorType, e.getMessage());
	}

}
