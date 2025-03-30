package controller;
/**
 * Custom exception thrown when a user tries to delete an order without selecting one
 */
public class EmptyOrderException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor 
	 */
	public EmptyOrderException() {
		super("");
	}
	/**
	 * A custom message is passed to notify user of the empty order exception
	 * @param message The custom message
	 */
	public EmptyOrderException(String message) {
		super(message);
	}
}
