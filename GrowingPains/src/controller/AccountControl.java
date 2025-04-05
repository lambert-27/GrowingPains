	package controller;
	
	import java.awt.Color;
	import java.sql.SQLException;
	
	import javax.swing.BorderFactory;
	import javax.swing.JPasswordField;
	import javax.swing.JTextField;
	
	import crud.CustomerCrud;
	import model.Address;
	import model.Customer;
	import view.GrowingPains;
	
	public class AccountControl {
		private CustomerCrud crud;
		
		public AccountControl() throws SQLException {
			crud = new CustomerCrud();
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
		public void createAccount(String fName, String lName, String email, String adrs, String password, String confirmPass, String phone) throws AccountCreationException, ValidationException {
	
			try {
	
				Address customerAdrs = new Address(adrs);
				
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
		 * 	 * Method which handles the account updating process
		 * On success (if passwords are equal and not empty as well as all other form fields containing
		 * some info, then update
		 * 
		 * @throws AccountCreationException For error with account creation
		 * @throws ValidationException note how we throw ValidationException, which means we can use our child exception classes 
		 * PasswordInconsistent and EmptyField, as ValidationException is their super class
		 * 
		 */
		public Customer updateAccount(Customer cust, String fName, String lName, String email, String adrs, String password, String confirmPass, String phone) throws AccountCreationException, ValidationException {
			try {
				Address customerAdrs = new Address(adrs);
		        // Hash the new password only after validation passes
		        String hashedPassword = PasswordHasher.hashPassword(password);
				//Create the customer for insertion
				cust = new Customer(cust.getCustomerID(), fName, lName, email, hashedPassword, phone, customerAdrs);
	
				//Finally, if the crud operation fails, throw an AccountCreationException
				if(!(crud.updateCustomer(cust))) {
					throw new AccountCreationException("Account updating failed");
				}
				//Catch an SQLException if all other Exception throws do not yield
			}catch (SQLException e) {
				throw new AccountCreationException("An error occured with your SQL Statement/Database: " + e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block for hashed password
				e.printStackTrace();
			}
			return cust;
		}
		
		/**
		 * 	 * Method which handles the account updating process
		 * On success (if passwords are equal and not empty as well as all other form fields containing
		 * some info, then update
		 * 
		 * @throws AccountCreationException For error with account creation
		 * @throws ValidationException note how we throw ValidationException, which means we can use our child exception classes 
		 * PasswordInconsistent and EmptyField, as ValidationException is their super class
		 * 
		 */
		public Customer updateAccountExclPass(Customer cust, String fName, String lName, String email, String adrs, String password, String confirmPass, String phone) throws AccountCreationException, ValidationException {
			try {
				Address customerAdrs = new Address(adrs);
				
				//Create the customer for insertion
				cust= new Customer(cust.getCustomerID(), fName, lName, email, password, phone, customerAdrs);
				
				//Finally, if the crud operation fails, throw an AccountCreationException
				if(!(crud.updateCustomerExclPass(cust))) {
					throw new AccountCreationException("Account updating failed");
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
			return cust;
		}
		
		
		public void checkFields(JTextField fName, JTextField lName, JTextField email, JTextField adrs, JTextField phone, JPasswordField password, JPasswordField confirmPass ) throws ValidationException {
			
			//Check for empty fields first
			checkInfo(fName, "First Name");
			checkInfo(lName, "Last Name");
			checkInfo(email, "Email");
			checkInfo(adrs, "Address");
			checkInfo(phone, "Phone");
			
			//Validate special requirements fields (regex patterns)
			validateForm(phone, email);
	
			//Finally, validate passwords
			validatePasswords(password, confirmPass);
	
		}
		
		public void checkFieldsEdit(JTextField fName, JTextField lName, JTextField email, JTextField adrs, JTextField phone) throws ValidationException {
			//Check for empty fields first
			checkInfo(fName, "First Name");
			checkInfo(lName, "Last Name");
			checkInfo(email, "Email");
			checkInfo(adrs, "Address");
			checkInfo(phone, "Phone");
			
			//Validate special requirements fields (regex patterns)
			validateForm(phone, email);
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
		public void validateForm(JTextField phone, JTextField email) throws ValidationException {
			try {
	
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
		public void validatePasswords(JPasswordField password, JPasswordField confirmPass) throws ValidationException {
		    String passwordText = new String(password.getPassword());
		    String confirmPassText = new String(confirmPass.getPassword());
		    
		    // Check if passwords are empty
		    if (passwordText.isEmpty() || confirmPassText.isEmpty()) {
		        password.setBorder(BorderFactory.createLineBorder(Color.RED));
		        throw new EmptyFieldException("Password fields cannot be empty");
		    }
		    
		    // Check if passwords match
		    if (!passwordText.equals(confirmPassText)) {
		        password.setBorder(BorderFactory.createLineBorder(Color.RED));
		        confirmPass.setBorder(BorderFactory.createLineBorder(Color.RED));
		        throw new PasswordInconsistentException("Passwords do not match");
		    }
		    
		    // Reset borders if valid
		    password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    confirmPass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		
		public void validateUpdatedPasswords(JPasswordField oldPassEntered, JPasswordField password, JPasswordField confirmPass, Customer cust) throws PasswordInconsistentException, Exception{
			
			String oldPassText = new String(oldPassEntered.getPassword());
			String passwordText = new String(password.getPassword());
		    String confirmPassText = new String(confirmPass.getPassword());
			// Hash the entered old password for comparison
	        String hashedOldPassEntered = PasswordHasher.hashPassword(oldPassText);
	        
	        
			//Check if the user has input the correct OLD password
			if(!(hashedOldPassEntered.equals(cust.getPassword()))) {
				//If the user doesn't, set the border to an error colour
				oldPassEntered.setBorder(BorderFactory.createLineBorder(Color.RED));
				throw new PasswordInconsistentException("Current password is incorrect");
			}
			//Check if the old password and new password are the same, if so, throw an exception
			if(oldPassEntered.equals(password)) {
				password.setBorder(BorderFactory.createLineBorder(Color.RED));
				throw new PasswordInconsistentException("New password cannot match old password");
			}
			//If the passwords don't match, throw a PasswordInconsistentException
			if(!(passwordText.equals(confirmPassText)))
			{
				password.setBorder(BorderFactory.createLineBorder(Color.RED));
				confirmPass.setBorder(BorderFactory.createLineBorder(Color.RED));
				throw new PasswordInconsistentException("Passwords do not match");
			}
			
		}
	}
