package controller;

public class InvalidEmailException extends ValidationException {
	
	private static final long serialVersionUID = 1L;

	public InvalidEmailException() {
		super ("");
	}
	
	public InvalidEmailException(String message) {
		super(message);
	}
}
