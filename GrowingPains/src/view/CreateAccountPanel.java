package view;
//GROWING PAINS - Mark Lambert - C00192497
//CreateAccountPanel View class - Contains structure for account creation 


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.AccountControl;
import exception.AccountCreationException;
import exception.EmptyFieldException;
import exception.PasswordInconsistentException;
import exception.ValidationException;

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
	private final AccountControl CONTROL = new AccountControl();;
	
	/**
	 * Constructs a new CreateAccountPanel, initialising the layout,  buttons and input fields
	 * 
	 * 
	 * @throws SQLException Error for DB operations
	 */
	public CreateAccountPanel() throws SQLException {		 
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
		
		
//		Button to submit account
		submit = GrowingButton.createButton("Submit");
		gbc.gridx = 0;
		gbc.gridy = 10;
		handleCreateEvent();
		add(submit, gbc);

//		Return back to login screen
		returnToLogin = GrowingButton.createButton("Return to Login");
		gbc.gridx = 1;
		gbc.gridy = 10;
		returnToLoginScreen();
		add(returnToLogin, gbc);
		
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
	 * Handles the insertion of a new Customer into the Customer table of the database
	 */
	public void handleCreateEvent() {
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try {
					CONTROL.checkFields(fName, lName, email, adrs, phone, password, confirmPass);
					//Once all of the fields have been checked, make variables for readability and pass them to createAccount 
					String fNameText = fName.getText();
					String lNameText = lName.getText();
					String emailText = email.getText();
					String adrsText = adrs.getText();
			//		Note for JPasswordField we cast the char array returned by getPassword to a String
					String passwordText = new String(password.getPassword());
					String confirmPassText = new String(confirmPass.getPassword());
					String phoneText = phone.getText();
					
					CONTROL.createAccount(fNameText, lNameText, emailText, adrsText, passwordText, confirmPassText, phoneText);
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
	}
	
	/**
	 * Returns the user to the login screen on click 
	 */
	public void returnToLoginScreen() {
		returnToLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GrowingPains.getCardLayout().show(GrowingPains.getMainContent(),  "Login");
			}			
		});
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
