package controller;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PaymentControl {
	
	
	public void checkFields(JTextField fName, JTextField lName, JTextField cardNumber, JPasswordField cvv, JComboBox<String> expiryMonth, JComboBox<String> expiryYear) throws ValidationException {
		checkInfo(fName, "First Name");
		checkInfo(lName, "Last Name");
		checkInfo(cardNumber, "Card Number");
		

		//Check if the CVV is exactly 3 digits long, we convert the character array from .getPassword to a new String
		if(!((new String(cvv.getPassword()).matches("[\\d]{3}")))) {
			cvv.setBorder(BorderFactory.createLineBorder(Color.RED));
			throw new ValidationException("CVV must be exactly 3 digits");
		}
		
		//Check if the card number is exactly 16 digits long
		if(!(cardNumber.getText().matches("[\\d]{16}"))) {
			cardNumber.setBorder(BorderFactory.createLineBorder(Color.RED));
			throw new ValidationException("Card Number must be exactly 16 digits long");
		}
		
		//Check if the user selected an expiry date by checking if the default value is still selected
		if((expiryMonth.getSelectedItem() == "--" || expiryYear.getSelectedItem() == "--")) {
			throw new ValidationException("Must select expiry date");
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
}
