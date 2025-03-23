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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import crud.CustomerCrud;
import model.Address;
import model.Customer;

public class CreateAccountPanel extends JPanel {
	/** 
	 * The CreateAccountPanel class represents the Create Account Panel in the GrowingPains application
	 * 
	 * It holds the structure and logic for handling the account creation process with the use of a JPasswordField
	 * for the password input. Before submission, some basic validation is done and on success a Customer object is
	 * created and sent to the database
	 */
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
	 * @param ARIAL the font used
	 * @param GREEN the colour used
	 * @param cardLayout the Layout Manager used
	 * @param mainContent The main panel that holds the cards
	 */
	public CreateAccountPanel(Font ARIAL, Color GREEN, CardLayout cardLayout, JPanel mainContent) throws SQLException {
		crud = new CustomerCrud();
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5); //Set padding
		
		buildForm(ARIAL, GREEN, cardLayout, mainContent);
				
	}
	
	/**
	 * Builds the form for account creation
	 * 
	 * @param ARIAL the font used
	 * @param GREEN the colour used
	 * @param cardLayout the Layout Manager used
	 * @param mainContent The main panel that holds the cards
	 */
	public void buildForm(Font ARIAL, Color GREEN, CardLayout cardLayout, JPanel mainContent) {
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
		submit = createButton("Submit", ARIAL, GREEN);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					createAccount(cardLayout, mainContent);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 10;
		add(submit, gbc);

//		Return back to login screen
		returnToLogin = createButton("Return to Login", ARIAL, GREEN);
		returnToLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(mainContent,  "Login");
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
	public JTextField createTextField() {
		JTextField txt = new JTextField();
		txt.setPreferredSize(new Dimension(300, 30));
		return txt;
		
	}
	/**
	 * Method which handles the account creation process
	 * On success (if passwords are equal and not empty as well as all other form fields containing
	 * some info, then insert
	 * 
	 */
	public boolean createAccount(CardLayout cardLayout, JPanel mainContent) throws SQLException {

		if(validateForm()) {
			String fName = this.fName.getText();
			String lName = this.lName.getText();
			String email = this.email.getText();
			String adrs = this.adrs.getText();
	//		Note for JPasswordField we cast the char array returned by getPassword to a String
			String password = new String(this.password.getPassword());
			String confirmPass = new String(this.password.getPassword());
			String phone = this.phone.getText();
			Address customerAdrs = new Address(adrs);
			
			if(password.equals(confirmPass) && !(password.isEmpty() || confirmPass.isEmpty())){
				Customer customer = new Customer(fName, lName, email, password, phone, customerAdrs);
				if(crud.insertCustomer(customer)) {
					cardLayout.show(mainContent, "Login");
					return true;
				}
				else {
					System.out.println("Error creating account!");
				}
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
}
