package controller;
//GROWING PAINS - Mark Lambert - C00192497
//EmptyFieldException class
/**
 * EmptyFieldException extends our custom ValidatioNException, this exception is thrown when 
 * the user does not input some data into an input field
 */
public class EmptyFieldException extends ValidationException {
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor for EmptyFieldException
	 */
	public EmptyFieldException() {
		
	}
	/**
	 * Constructs a new EmptyFieldException with a more detailed error message
	 * 
	 * @param fieldName The field name which is empty
	 */
	public EmptyFieldException(String fieldName) {
		super(fieldName + " cannot be empty");
	}
}
