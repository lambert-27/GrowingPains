package exception;
//GROWING PAINS - Mark Lambert - C00192497
//AccountEditException class

/**
* Custom exception thrown when an account creation fails due to some invalid data or error. 
* This exception provides constructors for both default and detailed error messages
*/
public class AccountEditException extends AccountCreationException {
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor for AccountEditException
	 */
	public AccountEditException() {
		
	}
	/**
	 * Constructs a new AccountEditException with a more detailed error message
	 * 
	 * @param message The detailed message explaining why the account editing process failed
	 */
	public AccountEditException(String message) {
		super(message);
	}

}
