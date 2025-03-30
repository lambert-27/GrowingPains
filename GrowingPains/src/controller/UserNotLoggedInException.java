package controller;
//GROWING PAINS - Mark Lambert - C00192497
//ValidationException class
/**
* A custom exception UserNotLoggedInException is thrown when the user inputs some invalid data
*/
public class UserNotLoggedInException extends Exception {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor for UserNotLoggedInException
	 */
	public UserNotLoggedInException() {
		super("");
	}
	/**
	 * Constructs a new UserNotLoggedInException with a more detailed error message
	 * 
	 * @param menuLocation The menu location name the user is attempting to navigate to
	 */
	public UserNotLoggedInException(String menuLocation) {
		super("Must be logged in before proceeding to " + menuLocation);
	}
}
