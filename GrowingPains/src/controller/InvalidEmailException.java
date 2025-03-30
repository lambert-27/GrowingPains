package controller;
/**
 * InvalidEmailException extends our custom ValidatioNException, this exception is thrown when 
 * the user enters invalid data into an email text field
 */
public class InvalidEmailException extends ValidationException {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor for InvalidEmailException
	 */
	public InvalidEmailException() {
		super ("");
	}
	/**
	 * Constructs a new InvalidEmailException with a more detailed error message
	 * 
	 * @param message THe custom message
	 */
	public InvalidEmailException(String message) {
		super(message);
	}
}
