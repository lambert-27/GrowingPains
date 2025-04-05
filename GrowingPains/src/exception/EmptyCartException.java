package exception;
/**
 * Custom exception thrown when a user tries to checkout an empty cart
 */
public class EmptyCartException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor 
	 */
	public EmptyCartException() {
		super("");
	}
	
	/**
	 * A custom message is passed to notify user of the empty cart exception
	 * @param message The custom message
	 */
	public EmptyCartException(String message) {
		super(message);
	}
}
