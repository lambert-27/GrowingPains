package controller;

public class EmptyOrderException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyOrderException() {
		super("");
	}
	
	public EmptyOrderException(String message) {
		super(message);
	}
}
