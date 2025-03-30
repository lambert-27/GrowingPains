package controller;
//GROWING PAINS - Mark Lambert - C00192497
//PasswordInconsistentException class
	/**
	 * PasswordInconsistentException extends our custom ValidatioNException, this exception is thrown when 
	 * the user attempts to change password, but the new password and confirm password fields do not match, 
	 * or if the user does not input the correct old password
	 */
public class PasswordInconsistentException extends ValidationException {
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor for PasswordInconsistentException
	 */
	public PasswordInconsistentException() {
		super("");
	}
	/**
	 * Constructs a new PasswordInconsistentException with a more detailed error message
	 * 
	 * @param message The detailed message explaining where the errors occurred
	 */
	public PasswordInconsistentException(String message) {
		super(message);
	}
}
