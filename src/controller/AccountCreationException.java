package controller;
//GROWING PAINS - Mark Lambert - C00192497
//AccountCreationException class

/*
 * Custom exception thrown when an account creation fails due to some invalid data or error. 
 * This exception provides constructors for both default and detailed error messages
 */
public class AccountCreationException extends Exception {

	private static final long serialVersionUID = 1L;
	/*
	 * Default Constructor for AccountCreationException
	 */
	public AccountCreationException() {
		
	}
	/*
	 * Constructs a new AccountCreatioNException with a more detailed error message
	 * 
	 * @param message The detailed message explaining why the account creation process failed
	 */
	public AccountCreationException(String message) {
		super(message);
	}
}
