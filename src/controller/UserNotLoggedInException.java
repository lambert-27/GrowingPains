package controller;
//GROWING PAINS - Mark Lambert - C00192497
//ValidationException class
/**
* A custom exception UserNotLoggedInException is thrown when the user inputs some invalid data
*/
public class UserNotLoggedInException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UserNotLoggedInException() {
		super("");
	}
	
	public UserNotLoggedInException(String menuLocation) {
		super("Must be logged in before proceeding to " + menuLocation);
	}
}
