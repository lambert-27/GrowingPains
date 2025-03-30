package controller;

public class InvalidEmailException extends ValidationException {
	
	public InvalidEmailException() {
		super ("");
	}
	
	public InvalidEmailException(String message) {
		super(message);
	}
}
