package exception;
//GROWING PAINS - Mark Lambert - C00192497
//ValidationException class
/**
 * A custom exception ValidationException is thrown when the user inputs some invalid data
 */
public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor for ValidationException
	 */
	public ValidationException() {
		super("");
	}
	/**
	 * Constructs a new ValidationException with a more detailed error message
	 * 
	 * @param message The detailed message explaining what was invalid
	 */
	public ValidationException(String message) {
		super(message);
	}
}
