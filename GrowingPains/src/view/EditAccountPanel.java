	package view;
	//GROWING PAINS - Mark Lambert - C00192497
	//EditAccountPanel View class - Contains structure for editing a logged in users account
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
	
	import controller.AccountControl;
import controller.PasswordHasher;
import exception.AccountEditException;
import exception.EmptyFieldException;
import exception.InvalidEmailException;
import exception.PasswordInconsistentException;
import exception.ValidationException;
import model.Customer;
	
/** 
 * The EditAccountPanel class represents the Create Account Panel in the GrowingPains application
 * 
 * It holds the structure and logic for handling the account editing process with the use of a JPasswordField
 * for the password input. Before submission, some basic validation is done and on success a Customer object is
 * edited and sent to the database. Error messages are displayed in accordance to the state of the users attempt 
 * at changing password. Visual cues are given when errors occur. 
 */
public class EditAccountPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	//Instance variables
	private JTextField fName;
	private JTextField lName;
	private JTextField email;
	private JTextField adrs;
	private JPasswordField oldPass;
	private JTextField phone;
	private JPasswordField password;
	private JPasswordField confirmPass;
	private JButton submit;
	private JButton returnToBrowse;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private Customer customer;
	private final AccountControl CONTROL = new AccountControl();

	/**
	 * 	
	/**
	 * Constructs a new EditAccountPanel, initialising the layout,  buttons and input fields
	 * 
	 * @param cust The currently logged in customer
	 * @throws SQLException for error with DB operations
	 */
	public EditAccountPanel(Customer cust) throws SQLException {
		customer = cust;
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5); //Set padding
		
		buildForm(cust);
	}
	
	/**
	 * Builds the form for account edit
	 * 
	 * @param cust The currently logged in Customer
	 */
	public void buildForm(Customer cust) {
		//First Name
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("First Name: "), gbc);
		
//		Add 1 to the y so that the inputbox is beneath the label
		gbc.gridy = 1;
		fName = createTextField(cust.getfName());
		add(fName, gbc);
		
		//Last Name
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(new JLabel("Last Name: "), gbc);
		
		gbc.gridy = 1;
		lName = createTextField(cust.getlName());
		add(lName, gbc);
		
		//Email (next line)
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(new JLabel("Email: "), gbc);
		
		gbc.gridy = 3;
		email = createTextField(cust.getEmail());
		add(email, gbc);
		

		//Phone
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(new JLabel("Phone: "), gbc);
		
		gbc.gridy = 3;
		phone = createTextField(cust.getPhone());
		add(phone, gbc);
		
		//Address (next line)
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(new JLabel("Address: "), gbc);
		
		gbc.gridy = 5;
		adrs = createTextField(cust.getAddress());
		add(adrs, gbc);
		
		//Old Password 
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(new JLabel("Old Password: "), gbc);
		
		gbc.gridy = 5;
		oldPass = new JPasswordField();
		oldPass.setPreferredSize(new Dimension(300, 30));
		add(oldPass, gbc);
		
		
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
		submit = GrowingButton.createButton("Submit");
		gbc.gridx = 0;
		gbc.gridy = 10;
		handleEditEvent();
		add(submit, gbc);

//		Return back to browse screen
		returnToBrowse = GrowingButton.createButton("Return to Browse");			
		gbc.gridx = 1;
		gbc.gridy = 10;
		returnToBrowse();
		add(returnToBrowse, gbc);
		
	}
	
	/**
	 * Create a text field method, encapsulates common code
	 * @param value The text to display on the text field
	 * @return the JTextField object
	 */
	public JTextField createTextField(String value) {
		JTextField txt = new JTextField(value);
		txt.setPreferredSize(new Dimension(300, 30));
		return txt;
		
	}
	
	/**
	 * Returns to the brwose screen on click
	 */
	public void returnToBrowse() {
		returnToBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Browse");
			}			
		});
	}
	
	/**
	 * Handles the event when a user presses the submit button to send the details to the database
	 */
	public void handleEditEvent() {
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try {
					
					updateAccount(customer); 
					//Display a success message when the user succesfully creates an account
					//EditAccountPanel.this ensures the OptionPane appears centered over the window
					//Message Text is the main content of the alert
					//Success is the title
					//INFORMATION_MESSAGE indicates the message type, in this case, for information
					JOptionPane.showMessageDialog(EditAccountPanel.this, "Account edited!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
				catch(AccountEditException e3) 
				{
					handleError(e3,  "Account Edit Failure");
//					e3.printStackTrace();
				}
				catch (ValidationException e4) 
				{
					handleError(e4, "Validation Error");
//					e4.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	/**
	 * Method which handles the account edit process
	 * On success (if passwords are equal and not empty as well as all other form fields containing
	 * some info, then update
	 * @param cust The currently logged in customer
	 * @throws AccountEditException For error with account edit
	 * @throws ValidationException note how we throw ValidationException, which means we can use our child exception classes 
	 * @throws PasswordInconsistentException Error for password validation  
	 * @return True/False if the update was succesful
	 */
	public boolean updateAccount(Customer cust) throws AccountEditException, ValidationException, PasswordInconsistentException, Exception {
	    try {
	    	CONTROL.checkFieldsEdit(fName, lName, email, adrs, phone);
	        
	        String fName = this.fName.getText();
	        String lName = this.lName.getText();
	        String email = this.email.getText();
	        String adrs = this.adrs.getText();
	        String oldPassEntered = new String(this.oldPass.getPassword());
	        String newPassword = new String(this.password.getPassword());
	        String confirmPassEntered = new String(this.confirmPass.getPassword());
	        String phone = this.phone.getText();
	        

	        //Check if the user has input something into one of the old password/new password fields, if so, require
	        //the user to commence the change password process
	        if(oldPassEntered.isBlank() || newPassword.isBlank()) {
	        	this.customer = CONTROL.updateAccountExclPass(cust, fName, lName, email, adrs, customer.getPassword(), confirmPassEntered, phone);
	        }
	        else {
	        	//Update all details
		        // Validate passwords before hashing
		        CONTROL.validateUpdatedPasswords(oldPass, password, confirmPass, cust);
		        
		        this.customer = CONTROL.updateAccount(customer, fName, lName, email, adrs, newPassword, confirmPassEntered, phone);
	        }
	                
	        GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Browse");
	        return true;
	    } catch (SQLException e) {
	        throw new AccountEditException("An error occurred with your SQL Statement/Database: " + e.getMessage());
	    }
	    
	}
	
	/**
	 * Valid a text field method, encapsulates common code
	 * 
	 * @param fieldName for displaying the correct field name in the error message
	 * @param txt The JTextField Object
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
			//Set the broder back to black, ensuring that when the user retries, the input box resets to default
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
			
			//Check if the phone number field contains digits and spaces only (A very simple phone number validation using regex pattern)
			if(!(phone.getText().matches("[\\d ]*"))) {
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
	 * @param oldPassEntered The Customers old password
	 * @param password The customers new Password
	 * @param confirmPass The confirmation of their password
	 * @param cust Current logged in customer
	 * @throws PasswordInconsistentException Exception for problem with password validation
	 */
	public void validatePasswords(String oldPassEntered, String password, String confirmPass, Customer cust) throws PasswordInconsistentException, Exception{
		
		// Hash the entered old password for comparison
        String hashedOldPassEntered = PasswordHasher.hashPassword(oldPassEntered);
        
        
		//Check if the user has input the correct OLD password
		if(!(hashedOldPassEntered.equals(cust.getPassword()))) {
			//If the user doesn't, set the border to an error colour
			oldPass.setBorder(BorderFactory.createLineBorder(Color.RED));
			throw new PasswordInconsistentException("Current password is incorrect");
		}
		//Check if the old password and new password are the same, if so, throw an exception
		if(oldPassEntered.equals(password)) {
			this.password.setBorder(BorderFactory.createLineBorder(Color.RED));
			throw new PasswordInconsistentException("New password cannot match old password");
		}
		//If the passwords don't match, throw a PasswordInconsistentException
		if(!(password.equals(confirmPass)))
		{
			this.password.setBorder(BorderFactory.createLineBorder(Color.RED));
			this.confirmPass.setBorder(BorderFactory.createLineBorder(Color.RED));
			throw new PasswordInconsistentException("Passwords do not match");
		}
		
	}
	
	/**
	 * Helper method used to display error messages to the user via a JOptionPane and write the error to an error file
	 * @param errorType The type of error that occured
	 * @param e The Exception occured
	 */
	private void handleError(Exception e, String errorType) {
		try {			
			//Write the error to screen and then to file
		    JOptionPane.showMessageDialog(EditAccountPanel.this, e.getMessage(), errorType, JOptionPane.ERROR_MESSAGE);
		    GrowingPains.errorWriter.logError(errorType, e.getMessage());
		    //Finally block to close file once operation is complete
		} finally {
			//errorWriter.closeFile();
		}

	}
	/**
	 * Returns the currently logged in customer, assigns the customer ID before returning
	 * @param cust Logged in customer, before edit
	 * @return Logged in customer after the edit
	 */
	public Customer getUpdatedCustomer(Customer cust) {
		customer.setCustomerID(cust.getCustomerID());
		return this.customer;
	}
}
