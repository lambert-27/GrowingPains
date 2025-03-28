package view;
//GROWING PAINS - Mark Lambert - C00192497
//EditAccountPanel View class - Contains structure for editing a logged in users account
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import crud.CustomerCrud;
import model.Address;
import model.Customer;

/** 
 * The EditAccountPanel class represents the Create Account Panel in the GrowingPains application
 * 
 * It holds the structure and logic for handling the account editing process with the use of a JPasswordField
 * for the password input. Before submission, some basic validation is done and on success a Customer object is
 * created and sent to the database. Error messages are displayed in accordance to the state of the users attempt 
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
	private CustomerCrud crud;
	private Customer customer;
	
	/**
	 * 	
	/**
	 * Constructs a new CreateAccountPanel, initialising the layout,  buttons and input fields
	 * 
	 * @param ARIAL the font used
	 * @param GREEN the colour used
	 * @param cl the Layout Manager used
	 * @param mainContent The main panel that holds the cards
	 * @param cust The currently logged in customer
	 */
	public EditAccountPanel(Font ARIAL, Color GREEN, CardLayout cl, JPanel mainContent, Customer cust) throws SQLException {
		//customer = new Customer();
		customer = cust;
		crud = new CustomerCrud();
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5); //Set padding
		
		buildForm(ARIAL, GREEN, cl, mainContent, cust);
		
	}
	
	/**
	 * Builds the form for account creation
	 * 
	 * @param ARIAL the font used
	 * @param GREEN the colour used
	 * @param cardLayout the Layout Manager used
	 * @param mainContent The main panel that holds the cards
	 */
	public void buildForm(Font ARIAL, Color GREEN, CardLayout cardLayout, JPanel mainContent, Customer cust) {
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
		submit = createButton("Submit", ARIAL, GREEN);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					updateAccount(cardLayout, mainContent, cust);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 10;
		add(submit, gbc);

//		Return back to browse screen
		returnToBrowse = createButton("Return to Browse", ARIAL, GREEN);
		returnToBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(mainContent,  "Browse");
			}			
		});
		
		gbc.gridx = 1;
		gbc.gridy = 10;
		add(returnToBrowse, gbc);
		
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
	
	/**
	 * Create a text field method, encapsulates common code
	 * 
	 */
	public JTextField createTextField(String value) {
		JTextField txt = new JTextField(value);
		txt.setPreferredSize(new Dimension(300, 30));
		return txt;
		
	}
	
	/**
	 * Method which handles the account creation process
	 * On success (if passwords are equal and not empty as well as all other form fields containing
	 * some info, then insert
	 * 
	 */
	public boolean updateAccount(CardLayout cardLayout, JPanel mainContent, Customer cust) throws SQLException {
		
		//Check for empty input fields
		if(validateForm()) {
			String fName = this.fName.getText();
			String lName = this.lName.getText();
			String email = this.email.getText();
			String adrs = this.adrs.getText();
			String oldPassEntered = new String(this.oldPass.getPassword());
			//Check if the user has input the correct OLD password
			if(!(oldPassEntered.equals(cust.getPassword()))) {
				//If the user doesn't, set the border to an error colour
				oldPass.setBorder(BorderFactory.createLineBorder(Color.RED));
				//Display an error message 
				showError("Old Pasword does not match!");
				return false;
			}
			else
				//Include an else for when the user retries, ensuring that the border gets updated
				oldPass.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	//		Note for JPasswordField we cast the char array returned by getPassword to a String
			String password = new String(this.password.getPassword());
			String confirmPass = new String(this.confirmPass.getPassword());
			String phone = this.phone.getText();
			Address customerAdrs = new Address(adrs);
			
			//Check to ensure the passwords entered match/if they are empty
			if(password.equals(confirmPass) && !(password.isEmpty() || confirmPass.isEmpty())){
				if(password.equals(oldPassEntered)) {
					this.password.setBorder(BorderFactory.createLineBorder(Color.RED));
					showError("Your new password must be unique to your old password!");
					return false;
				}
				else
					this.password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				customer = new Customer(fName, lName, email, password, phone, customerAdrs);
				if(crud.updateCustomer(customer)) {
					cardLayout.show(mainContent, "Browse");
					return true;
				}
			}
			else {
				this.password.setBorder(BorderFactory.createLineBorder(Color.RED));
				this.confirmPass.setBorder(BorderFactory.createLineBorder(Color.RED));
				showError("Passwords do not match!");
				return false;
			}

		}
		return false;
	}
	
	/**
	 * Valid a text field method, encapsulates common code
	 * 
	 */
	public boolean checkInfo(JTextField txt) {
//		Basic validation to check if the user has atleast input some info in each textbox
		if(txt.getText().isEmpty()) {
			
			txt.setBorder(BorderFactory.createLineBorder(Color.RED));
			return false;
		}
		else
//			Set it back to black if it's valid
			txt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			return true;
	}
	
	/**
	 * Iterates through each text field in the form to check for empty inputs
	 * 
	 */
	public boolean validateForm() {
		boolean isValid = true;
		
		if(checkInfo(fName) == false) {
			isValid = false;
		}
		if(checkInfo(lName) == false) {
			isValid = false;
		}
		if(checkInfo(email) == false) {
			isValid = false;
		}
		if(checkInfo(adrs) == false) {
			isValid = false;
		}
		if(checkInfo(phone) == false) {
			isValid = false;
		}
		
		return isValid;
	}
	
	/**
	 * Helper variable used to display error messages to the user via a JOptionPane
	 * @param message
	 */
	private void showError(String message) {
	    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}
